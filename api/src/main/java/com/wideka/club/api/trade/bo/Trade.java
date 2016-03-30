package com.wideka.club.api.trade.bo;

import java.math.BigDecimal;
import java.util.List;

import com.wideka.club.framework.bo.SearchInfo;

/**
 * 交易.
 * 
 * @author JiakunXu
 * 
 */
public class Trade extends SearchInfo {

	private static final long serialVersionUID = -1761794880999676421L;

	/**
	 * 交易ID.
	 */
	private Long tradeId;

	/**
	 * 用户ID.
	 */
	private String userId;

	/**
	 * 店铺ID.
	 */
	private Long shopId;

	/**
	 * 交易价格（不含折扣）.
	 */
	private BigDecimal tradePrice;

	/**
	 * 交易积分（积分兑换）.
	 */
	private BigDecimal tradePoints;

	/**
	 * 优惠券使用价格.
	 */
	private BigDecimal couponPrice;

	/**
	 * 邮费.
	 */
	private BigDecimal postage;

	/**
	 * 涨价或减价.
	 */
	private BigDecimal change;

	/**
	 * 星星级别，店小儿分类交易.
	 */
	private int score;

	/**
	 * 备注，店小儿分类交易.
	 */
	private String remark;

	/**
	 * check: 临时订单; topay: 待付款; tosend: 待发货; send: 已发货; sign: 标记签收; cancel: 已关闭; feedback:
	 * 维权订单; feedbacked: 已处理维权订单.
	 */
	private String type;

	/**
	 * 交易订单号.
	 */
	private String tradeNo;

	/**
	 * 状态 D:删除 U:正常.
	 */
	private String state;

	/**
	 * 下单时间.
	 */
	private String createDate;

	/**
	 * 订单最后修改时间.
	 */
	private String modifyDate;

	/**
	 * 买家付款时间.
	 */
	private String payDate;

	/**
	 * 卖家发货时间.
	 */
	private String sendDate;

	/**
	 * 物流标记签收时间.
	 */
	private String signDate;

	/**
	 * 维权时订单类型.
	 */
	private String feedbackType;

	/**
	 * 买家维权时间.
	 */
	private String feedbackDate;

	/**
	 * 卖家处理维权时间.
	 */
	private String feedbackedDate;

	/**
	 * 操作人ID.
	 */
	private String modifyUser;

	/**
	 * 收货人的姓名.
	 */
	private String receiverName;

	/**
	 * 收货人的所在省份.
	 */
	private String receiverProvince;

	/**
	 * 收货人的所在市.
	 */
	private String receiverCity;

	/**
	 * 收货人的所在区.
	 */
	private String receiverArea;

	/**
	 * 收货人的所在区对应id.
	 */
	private String receiverBackCode;

	/**
	 * 收货人的详细地址.
	 */
	private String receiverAddress;

	/**
	 * 收货人的邮编.
	 */
	private String receiverZip;

	/**
	 * 收货人的手机号码或固定电话.
	 */
	private String receiverTel;

	/**
	 * 给卖家留言.
	 */
	private String receiverRemark;

	/**
	 * 购物车(存在多个id: 1,2,3,4,5).
	 */
	private String cartId;

	/**
	 * 是否需要物流 Y or N.
	 */
	private String shipment;

	/**
	 * 支付方式.
	 */
	private String payType;

	// >>>>>>>>>>以下是辅助属性<<<<<<<<<<

	/**
	 * 公司id.
	 */
	private String companyId;

	/**
	 * 订单行项目.
	 */
	private List<Order> orderList;

	/**
	 * 订单物流信息.
	 */
	private List<TradeExpress> tradeExpressList;

	/**
	 * 统计维度 日期 2014-07-16.
	 */
	private String dates;

	/**
	 * 统计值.
	 */
	private int count;

	/**
	 * 统计值 下单数量.
	 */
	private int countOfCreate;

	/**
	 * 统计值 付款数量.
	 */
	private int countOfPay;

	/**
	 * 统计值 发货数量.
	 */
	private int countOfSend;

	/**
	 * 统计值 签收数量.
	 */
	private int countOfSign;

	/**
	 * 统计值 取消数量.
	 */
	private int countOfCancel;

	public Long getTradeId() {
		return tradeId;
	}

	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public BigDecimal getTradePrice() {
		return tradePrice;
	}

	public void setTradePrice(BigDecimal tradePrice) {
		this.tradePrice = tradePrice;
	}

	public BigDecimal getTradePoints() {
		return tradePoints;
	}

	public void setTradePoints(BigDecimal tradePoints) {
		this.tradePoints = tradePoints;
	}

	public BigDecimal getCouponPrice() {
		return couponPrice;
	}

	public void setCouponPrice(BigDecimal couponPrice) {
		this.couponPrice = couponPrice;
	}

	public BigDecimal getPostage() {
		return postage;
	}

	public void setPostage(BigDecimal postage) {
		this.postage = postage;
	}

	public BigDecimal getChange() {
		return change;
	}

	public void setChange(BigDecimal change) {
		this.change = change;
	}

	/**
	 * 实付金额 tradePrice - couponPrice + postage + (change).
	 * 
	 * @return
	 */
	public BigDecimal getPrice() {
		if (this.tradePrice != null) {
			return this.tradePrice.add(this.couponPrice.negate()).add(this.postage).add(this.change);
		}

		return BigDecimal.ZERO;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getPayDate() {
		return payDate;
	}

	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public String getSignDate() {
		return signDate;
	}

	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}

	public String getFeedbackType() {
		return feedbackType;
	}

	public void setFeedbackType(String feedbackType) {
		this.feedbackType = feedbackType;
	}

	public String getFeedbackDate() {
		return feedbackDate;
	}

	public void setFeedbackDate(String feedbackDate) {
		this.feedbackDate = feedbackDate;
	}

	public String getFeedbackedDate() {
		return feedbackedDate;
	}

	public void setFeedbackedDate(String feedbackedDate) {
		this.feedbackedDate = feedbackedDate;
	}

	public String getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverProvince() {
		return receiverProvince;
	}

	public void setReceiverProvince(String receiverProvince) {
		this.receiverProvince = receiverProvince;
	}

	public String getReceiverCity() {
		return receiverCity;
	}

	public void setReceiverCity(String receiverCity) {
		this.receiverCity = receiverCity;
	}

	public String getReceiverArea() {
		return receiverArea;
	}

	public void setReceiverArea(String receiverArea) {
		this.receiverArea = receiverArea;
	}

	public String getReceiverBackCode() {
		return receiverBackCode;
	}

	public void setReceiverBackCode(String receiverBackCode) {
		this.receiverBackCode = receiverBackCode;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public String getReceiverZip() {
		return receiverZip;
	}

	public void setReceiverZip(String receiverZip) {
		this.receiverZip = receiverZip;
	}

	public String getReceiverTel() {
		return receiverTel;
	}

	public void setReceiverTel(String receiverTel) {
		this.receiverTel = receiverTel;
	}

	public String getReceiverRemark() {
		return receiverRemark;
	}

	public void setReceiverRemark(String receiverRemark) {
		this.receiverRemark = receiverRemark;
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public String getShipment() {
		return shipment;
	}

	public void setShipment(String shipment) {
		this.shipment = shipment;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

	public List<TradeExpress> getTradeExpressList() {
		return tradeExpressList;
	}

	public void setTradeExpressList(List<TradeExpress> tradeExpressList) {
		this.tradeExpressList = tradeExpressList;
	}

	public String getDates() {
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCountOfCreate() {
		return countOfCreate;
	}

	public void setCountOfCreate(int countOfCreate) {
		this.countOfCreate = countOfCreate;
	}

	public int getCountOfPay() {
		return countOfPay;
	}

	public void setCountOfPay(int countOfPay) {
		this.countOfPay = countOfPay;
	}

	public int getCountOfSend() {
		return countOfSend;
	}

	public void setCountOfSend(int countOfSend) {
		this.countOfSend = countOfSend;
	}

	public int getCountOfSign() {
		return countOfSign;
	}

	public void setCountOfSign(int countOfSign) {
		this.countOfSign = countOfSign;
	}

	public int getCountOfCancel() {
		return countOfCancel;
	}

	public void setCountOfCancel(int countOfCancel) {
		this.countOfCancel = countOfCancel;
	}

}
