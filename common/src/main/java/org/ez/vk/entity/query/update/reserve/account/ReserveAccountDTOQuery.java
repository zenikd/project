package org.ez.vk.entity.query.update.reserve.account;

import org.ez.vk.entity.db.constant.AccountConst;
import org.ez.vk.entity.query.DBQuery;
import org.ez.vk.entity.query.constant.Operators;
import org.ez.vk.entity.query.update.UpdateDTOQuery;
import org.ez.vk.entity.query.update.reserve.ReserveDTOQuery;

public class ReserveAccountDTOQuery extends ReserveDTOQuery {
	private DBQuery resetFiled = new DBQuery();

	public ReserveAccountDTOQuery() {
		setResetFiled();
	}

	private void setResetFiled() {
		resetFiled.addSearchParam(Operators.$SET, AccountConst.COUNT_COMMENT, 0);
		resetFiled.addSearchParam(Operators.$SET, AccountConst.COUNT_LOAD, 0);
		resetFiled.addSearchParam(Operators.$SET, AccountConst.COUNT_QUERY, 0);
	}

	public DBQuery getResetFiled() {
		return resetFiled;
	}

	public void setResetFiled(DBQuery resetFiled) {
		this.resetFiled = resetFiled;
	}
	
	
}
