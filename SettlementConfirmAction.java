package com.internousdev.earth.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.earth.dao.CartInfoDAO;
import com.internousdev.earth.dao.DestinationInfoDAO;
import com.internousdev.earth.dto.CartInfoDTO;
import com.internousdev.earth.dto.DestinationInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class SettlementConfirmAction extends ActionSupport implements SessionAware {
	// private List<CartInfoDTO> cartInfoDTO = new ArrayList<CartInfoDTO>();
	private List<DestinationInfoDTO> destinationInfoDTO = new ArrayList<DestinationInfoDTO>();
	private Map<String, Object> session;
	private List<CartInfoDTO> cartInfoDTO;
	String result;
	@Override
	public String execute(){
		if (session.isEmpty()) {
			return "sessionTimeout";
		}
		/**
		 * 決済ボタン押下(カート画面) ログイン判定
		 */
		// 未ログインだったらログイン画面へ
		if (session.get("logined").equals(0)) {
			// ログイン機能でカート画面から遷移してきた場合、認証成功でカート画面に戻るのでflgを渡す。
			session.put("cartflg", "1");
			result = "login";
		}
		// ログインしてたら宛先情報確認
		else {
			@SuppressWarnings("unchecked")
			List<CartInfoDTO> cartInfoList = (List<CartInfoDTO>) session.get("cartinfo");

			CartInfoDAO cartInfoDAO = new CartInfoDAO();
			List<CartInfoDTO> cartInfoDTOList = new ArrayList<CartInfoDTO>();
			cartInfoDTOList = cartInfoDAO.getCartContents(session.get("loginuserid").toString());
			//セッションの中身の確認
			if(cartInfoList.size() == cartInfoDTOList.size()) {

			} else {
				session.put("cartinfo", cartInfoDTOList);
			}

			DestinationInfoDAO destinationInfoDAO = new DestinationInfoDAO();
			// getDestinationInfoメソッドを呼び出し、引数にString型のuserIdを渡す
			// ユーザーIDごとの宛先情報をselectしてdestinationInfoDTOに格納
			// jsp側でdestinationInfoDTOのnull判定をする(宛先情報判定)
			// null:宛先情報はありません。
			// !null:宛先情報を選択して下さい。
			destinationInfoDTO = destinationInfoDAO.destinationInfo(session.get("loginuserid").toString());

			result = SUCCESS;
		}
		return result;
	}
	public List<CartInfoDTO> getCartInfoDTO() {
	 return cartInfoDTO;
	 }

	 public void setCartInfoDTO(List<CartInfoDTO> cartInfoDTO) {
	 this.cartInfoDTO = cartInfoDTO;
	 }

	public List<DestinationInfoDTO> getDestinationInfoDTO() {
		return destinationInfoDTO;
	}

	public void setDestinationInfoDTO(List<DestinationInfoDTO> destinationInfoDTO) {
		this.destinationInfoDTO = destinationInfoDTO;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
