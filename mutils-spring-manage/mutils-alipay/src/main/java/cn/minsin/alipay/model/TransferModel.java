package cn.minsin.alipay.model;

import java.math.BigDecimal;

import cn.minsin.core.annotation.NotNull;
import cn.minsin.core.rule.ModelRule;
import cn.minsin.core.tools.NumberUtil;

/**
 * 支付宝转账
 * @author mintonzhang
 * @date 2019年1月10日
 */
public class TransferModel extends ModelRule {

	/**
	* 
	*/
	private static final long serialVersionUID = 5266308521996557923L;

	@NotNull("接入方生成的订单号")
	private String out_biz_no;

	@NotNull("收款方账户类型")
	private String payee_type;

	@NotNull("收款方支付宝账号")
	private String payee_account;

	@NotNull("转账金额 大于0 且最多两位小数")
	private BigDecimal amount;

	@NotNull("转账名称")
	private String payer_show_name;

	@NotNull("提现用户的真实姓名")
	private String payee_real_name;

	@NotNull("转账备注")
	private String remark;

	public String getOut_biz_no() {
		return out_biz_no;
	}

	public void setOut_biz_no(String out_biz_no) {
		this.out_biz_no = out_biz_no;
	}

	public String getPayee_type() {
		return payee_type;
	}

	public void setPayee_type(String payee_type) {
		this.payee_type = payee_type;
	}

	public String getPayee_account() {
		return payee_account;
	}

	public void setPayee_account(String payee_account) {
		this.payee_account = payee_account;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		if(amount!=null) {
			amount =NumberUtil.keepLenthDecimals(2, amount);
		}
		this.amount = amount;
	}

	public String getPayer_show_name() {
		return payer_show_name;
	}

	public void setPayer_show_name(String payer_show_name) {
		this.payer_show_name = payer_show_name;
	}

	public String getPayee_real_name() {
		return payee_real_name;
	}

	public void setPayee_real_name(String payee_real_name) {
		this.payee_real_name = payee_real_name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
