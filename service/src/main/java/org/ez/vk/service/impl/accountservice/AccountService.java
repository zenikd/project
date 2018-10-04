package org.ez.vk.service.impl.accountservice;

import org.ez.api.dao.IAccountDao;
import org.ez.entity.vk.db.reserved.AccountVk;
import org.ez.vk.service.api.IAccountService;
import org.ez.vk.service.common.web.WebHelper;
import org.ez.vk.service.entity.AccountServiceDTO;
import org.ez.vk.service.impl.AbstractService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.account.UserSettings;

@Service
public class AccountService extends AbstractService implements IAccountService {
	@Autowired
	IAccountDao accountDao;

	public final static String ACCOUNT_ALREADY_EXIST = "account already exists";
	private final static String BAD_CREDENTIALS = "Login or pass wrong";
	private final static String VK_API_EXCEPTION = "Vk api exception";
	private final static String VK_DOMEN = "https://vk.com";
	private final static String ID = "id";

	private final static TransportClient transportClient = HttpTransportClient.getInstance();
	private final static VkApiClient vk = new VkApiClient(transportClient);

	public String addAccount(AccountServiceDTO accountServiceDTO) {
		String urlToGetAccount = getURLToGetAccount(accountServiceDTO);
		String response = WebHelper.gerStringByUrl(urlToGetAccount);

		AccountVk defaultAccount = getDefaultAccount(response);
		if (defaultAccount.getUserActor().getAccessToken() == null) {
			return BAD_CREDENTIALS;
		}

		try {
			setAccountIdentificationData(defaultAccount);
		} catch (Exception e) {
			return VK_API_EXCEPTION;
		}
		setUserCredentials(accountServiceDTO, defaultAccount);		
		try {
			accountDao.addAccount(defaultAccount);
		} catch (RuntimeException e) {
			return ACCOUNT_ALREADY_EXIST;
		}
		return "ok";
	}

	private void setUserCredentials(AccountServiceDTO accountServiceDTO, AccountVk defaultAccount) {
		defaultAccount.setType(accountServiceDTO.getType());
		defaultAccount.setUserLogin(accountServiceDTO.getLogin());
		defaultAccount.setUserPass(accountServiceDTO.getPass());
	}

	private AccountVk getDefaultAccount(String response) {
		JSONObject obj = new JSONObject(response);
		String accessToken = obj.getString("access_token");
		Integer userId = obj.getInt("user_id");
		AccountVk accountVk = new AccountVk();
		accountVk.setUserActor(new UserActor(userId, accessToken));
		return accountVk;

	}

	private void setAccountIdentificationData(AccountVk defaultAccount) throws ApiException, ClientException {
		UserSettings accountInfo = vk.account().getProfileInfo(defaultAccount.getUserActor()).execute();

		Integer idValue = defaultAccount.getUserActor().getId();
		defaultAccount.setDefaultAccountUrl(VK_DOMEN + "/" + ID + idValue);
		if (accountInfo.getScreenName() != null) {
			defaultAccount.setCustomAccountUrl(VK_DOMEN + "/" + accountInfo.getScreenName());
		}
		defaultAccount.setUserName(String.format("%s %s", accountInfo.getFirstName(), accountInfo.getLastName()));

	}

	private String getURLToGetAccount(AccountServiceDTO accountServiceDTO) {
		return String.format(
				"https://oauth.vk.com/token?grant_type=password&client_id=2274003&client_secret=hHbZxrka2uZ6jB1inYsH&username=%s&password=%s",
				accountServiceDTO.getLogin(), accountServiceDTO.getPass());
	}

	

}
