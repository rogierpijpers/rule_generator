package data;

import java.sql.ResultSet;
import java.util.ArrayList;

import util.CategoryDTO;

public class CategoryDAO {

	private CategoryDTO categoryDTO;

	public ArrayList<CategoryDTO> getAllCategories() {
		ArrayList<CategoryDTO> categoryDTOList = new ArrayList<>();
		DatabaseConnection connection = null;
		try {
			connection = new DatabaseConnection();
			ResultSet result = connection.query("SELECT * FROM CATEGORY ORDER BY id");

			while (result.next()) {
				categoryDTOList.add(new CategoryDTO(result.getString(2)));
			}
			result.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return categoryDTOList;

	}

	public CategoryDTO getCategory(String name) {
		DatabaseConnection connection = null;
		try {
			connection = new DatabaseConnection();
			ResultSet result = connection.query("SELECT * FROM CATEGORY WHERE NAME = '" + name + "'");

			while (result.next()) {
				categoryDTO = new CategoryDTO(result.getString(2));
			}
			result.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return categoryDTO;

	}
}
