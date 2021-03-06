package org.ez.vk.service.impl.account;

import java.io.IOException;

import org.ez.vk.db.AccountDao;
import org.ez.vk.entity.AccountServiceDTO;
import org.ez.vk.entity.db.factory.AccountFactory;
import org.ez.vk.entity.db.reservable.AccountVk;
import org.ez.vk.exception.internal.InternalException;
import org.ez.vk.exception.user.BadCredentialsException;
import org.ez.vk.exception.user.RootUserException;
import org.ez.vk.helper.web.WebHelper;
import org.ez.vk.service.AccountService;
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
public class AccountServiceImpl implements AccountService
{
	@Autowired
	AccountFactory accountFactory;
	@Autowired
	AccountDao accountDao;
	@Autowired
	WebHelper webHelper;

	public final static String ACCOUNT_ALREADY_EXIST = "account already exists";
	private final static String BAD_CREDENTIALS = "Login or pass wrong";
	private final static String VK_DOMEN = "https://vk.com";
	private final static String ID = "id";

	private final static TransportClient transportClient = HttpTransportClient.getInstance();
	private final static VkApiClient vk = new VkApiClient(transportClient);

	public void addAccount(AccountServiceDTO accountServiceDTO) throws RootUserException, InternalException {
		String urlToGetAccount = getURLToGetAccount(accountServiceDTO);
		String response;
		try {
			response = webHelper.getStringByUrl(urlToGetAccount);		
		} catch (IOException e) {
			throw new BadCredentialsException(BAD_CREDENTIALS);
		}
		AccountVk accountVk = getDefaultAccount(response);
		setAccountIdentificationData(accountVk);
		setUserCredentials(accountServiceDTO, accountVk);
		accountDao.addEntity(accountVk);

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
		AccountVk accountVk = accountFactory.getEntity();
		accountVk.setUserActor(new UserActor(userId, accessToken));
		return accountVk;

	}

	private void setAccountIdentificationData(AccountVk defaultAccount) throws InternalException {
		UserSettings accountInfo;
		try {
			accountInfo = vk.account().getProfileInfo(defaultAccount.getUserActor()).execute();
			Integer idValue = defaultAccount.getUserActor().getId();
			defaultAccount.setDefaultAccountUrl(VK_DOMEN + "/" + ID + idValue);
			if (accountInfo.getScreenName() != null) {
				defaultAccount.setCustomAccountUrl(VK_DOMEN + "/" + accountInfo.getScreenName());
			}
			defaultAccount.setUserName(String.format("%s %s", accountInfo.getFirstName(), accountInfo.getLastName()));
		} catch (ApiException e) {
			throw new InternalException();
		} catch (ClientException e) {
			throw new InternalException();
		}

	}

	private String getURLToGetAccount(AccountServiceDTO accountServiceDTO) {
		return String.format(
				"https://oauth.vk.com/token?grant_type=password&client_id=2274003&client_secret=hHbZxrka2uZ6jB1inYsH&username=%s&password=%s",
				accountServiceDTO.getLogin(), accountServiceDTO.getPass());
	}

}
