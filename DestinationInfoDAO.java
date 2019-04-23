package com.internousdev.earth.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.internousdev.earth.dto.DestinationInfoDTO;
import com.internousdev.earth.util.DBConnector;

public class DestinationInfoDAO {

	public int insert(String userId, String familyName, String firstName,String familyNameKana, String firstNameKana,
		String userAddress, String email, String telNumber) {
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		int count = 0;

		String sql = "INSERT INTO destination_info "
				+	 "(user_id, family_name, first_name, family_name_kana, first_name_kana, "
				+	 "user_address, email, tel_number, regist_date, update_date)"
				+ "VALUES(?,?,?,?,?,?,?,?,now(),now())";

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,userId);
			preparedStatement.setString(2,familyName);
			preparedStatement.setString(3,firstName);
			preparedStatement.setString(4,familyNameKana);
			preparedStatement.setString(5,firstNameKana);
			preparedStatement.setString(6,userAddress);
			preparedStatement.setString(7,email);
			preparedStatement.setString(8,telNumber);
			count=preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}


	public List<DestinationInfoDTO> destinationInfo(String userId){
		DBConnector db = new DBConnector();
		Connection connection = db.getConnection();
		List<DestinationInfoDTO> destinationInfoDTO = new ArrayList<DestinationInfoDTO>();

		String sql = "SELECT id, family_name, first_name, family_name_kana, first_name_kana, user_address, tel_number, email FROM destination_info WHERE user_id = ? ORDER BY regist_date ASC;";

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, userId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				DestinationInfoDTO dto = new DestinationInfoDTO();
				dto.setId(rs.getInt("id"));
				dto.setFamilyName(rs.getString("family_name"));
				dto.setFirstName(rs.getString("first_name"));
				dto.setFamilyNameKana(rs.getString("family_name_kana"));
				dto.setFirstNameKana(rs.getString("first_name_kana"));
				dto.setUserAddress(rs.getString("user_address"));
				dto.setEmail(rs.getString("email"));
				dto.setTelNumber(rs.getString("tel_number"));
				destinationInfoDTO.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
		return destinationInfoDTO;
	}
}
