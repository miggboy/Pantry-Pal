package group_2_cs2043.Frontend;

import java.io.IOException;
import java.util.ArrayList;
import group_2_cs2043.Backend.Ingredient; //TODO: This is unused?
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *This is a controller class for the Add Ingredient pop-up screen.
 *
 * @author Benjamin Martin
 * 
 */

public class AddIngredientController {

	@FXML
	private Button enterButton;

	@FXML
	private TextField ingredientField;
	
	@FXML
	private Label errorText;

  /**
   * This ActionEvent handles creating a new Ingredient and storing it.
   * Checks for blank input and duplicates.
   * @throws IOException  
   * @author Benjamin Martin & Miguel Daigle Gould
   */
  @FXML
  void onEnterClick(ActionEvent event) throws IOException {
	  String ingredientName = ingredientField.getText();
	  
	  if(!(ingredientName.isBlank() || ingredientName.isEmpty())) {
		  Ingredient ingredient = new Ingredient(ingredientName, false);
		  ArrayList<Ingredient> ingredientArray = Ingredient.loadSavedList();
		  
		  //Testing shows .contains always returns false; whether the list contains the Ingredient or not
		  //Maybe .equals in Ingredient needs to be @Override'd?
		  //Manual iteration is in place for now
		  //**
		  boolean containsIngredient = false;
		  for(int i = 0; i < ingredientArray.size(); i++) {
			  if(ingredientArray.get(i).getName().toLowerCase().equals(ingredientName.toLowerCase())) {
				  containsIngredient = true;
				  break;
			  }
		  }
		  //**
		  
		  if(!containsIngredient) {
			  ingredientArray.add(ingredient);
			  Ingredient.writeCurrentList(ingredientArray);
			  Stage stage = (Stage) enterButton.getScene().getWindow();
		      stage.close();
		  }
		  else {
			  errorText.setTextFill(Color.RED);
			  errorText.setText("Error: List already contains ingredient");
		  }
	  }
	  else {
		  errorText.setTextFill(Color.RED);
		  errorText.setText("Error: Please enter an ingredient");		  
	  }
  }
}
