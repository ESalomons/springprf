package springTutorial.dao.jdbcDaos;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import springTutorial.model.Account;

public class BalanceUpdateDAOImpl extends JdbcDaoSupport implements
		springTutorial.dao.interfaces.BalanceUpdateDAO {

	@Override
	public void updateAccountBalance(Account account) {
		
		String sql = "select sum(amount) from money_transactions where source_account_id = ?";
		Float min = (Float) getJdbcTemplate().queryForObject(sql,
				new Object[] { account.getId() }, Float.class);
		if(min == null){
			min = 0f;
		}
		sql = "select sum(amount) from money_transactions where target_account_id = ?";
		Float plus = (Float) getJdbcTemplate().queryForObject(sql,
				new Object[] { account.getId() }, Float.class);
		if(plus == null){
			plus = 0f;
		}
		
		Float amount = plus - min;
		
		sql = "update accounts set balance = ? where id = ?";
		getJdbcTemplate().update(sql, new Object[]{account.getBalance() + amount, account.getId()});
	}

}
