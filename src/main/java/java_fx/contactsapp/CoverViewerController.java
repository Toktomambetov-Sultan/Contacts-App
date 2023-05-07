package java_fx.contactsapp;// CoverViewerController.java
// Controller for Cover Viewer application
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.util.Callback;

import java.io.File;

public class CoverViewerController {
   // instance variables for interacting with GUI
   @FXML private ListView<Contact> booksListView;
   // stores the list of Book Objects
   private final ObservableList<Contact> contacts =
      FXCollections.observableArrayList();

    @FXML
    private TextField EmailLabel;

    @FXML
    private TextField FirstnameLabel;

    @FXML
    private TextField LastnameLabel;

    @FXML
    private TextField PhoneNumberLabel;
    @FXML
    private ImageView ImageViewCover;
    private String PhotoUrl = "";

   public void initialize() {
      booksListView.setItems(contacts); // bind booksListView to books

      // when ListView selection changes, show large cover in ImageView
      booksListView.getSelectionModel().selectedItemProperty().
         addListener(
            new ChangeListener<Contact>() {
               @Override                                                     
               public void changed(ObservableValue<? extends Contact> ov,
                                   Contact oldValue, Contact newValue) {
                   FirstnameLabel.setText(newValue.getFirstname());
                   LastnameLabel.setText(newValue.getLastname());
                   EmailLabel.setText(newValue.getEmail());
                   PhoneNumberLabel.setText(newValue.getPhoneNumber());
                   String photo = newValue.getPhoto();
                   updateImageView(photo);
               }
            }
         );        
         
      // set custom ListView cell factory
      booksListView.setCellFactory(
         new Callback<ListView<Contact>, ListCell<Contact>>() {
            @Override
            public ListCell<Contact> call(ListView<Contact> listView) {
               return new ContactTextCell();
            }
         }
      );
   }
   @FXML
   void CreateAction(ActionEvent event) {
        booksListView.getItems().add(
                new Contact(FirstnameLabel.getText(),LastnameLabel.getText(),EmailLabel.getText(),PhoneNumberLabel.getText(),PhotoUrl==""?"":PhotoUrl)
        );
   }

    @FXML
   void UpdateAction(ActionEvent event){
       Contact selectedItem = booksListView.getSelectionModel().getSelectedItem();
       selectedItem.setFirstname( FirstnameLabel.getText() );
       selectedItem.setLastname( LastnameLabel.getText() );
       selectedItem.setEmail( EmailLabel.getText());
       selectedItem.setPhoneNumber(PhoneNumberLabel.getText());
        selectedItem.setPhoto(PhotoUrl);

       // First update the item in the model
       booksListView.getItems().set( booksListView.getSelectionModel().getSelectedIndex(), selectedItem );
       // Trigger a sort of the items. Could be avoided by checking if the title really did change
//       listView.getItems().sort( new NoteComparator() );
   }

   @FXML
   void DeleteAction(ActionEvent event){
       int index = booksListView.getSelectionModel().getSelectedIndex();
       booksListView.getSelectionModel().clearSelection();
       booksListView.getItems().remove( index );
       booksListView.refresh();
   }

    @FXML
    void UploadAction(ActionEvent event){
        FileChooser fc = new FileChooser();
        File f = fc.showOpenDialog(null);
        if(f!=null){
            this.PhotoUrl = f.getAbsolutePath();
            updateImageView(this.PhotoUrl);
        }else{
            this.PhotoUrl = "";
        }
    }
    @FXML
    void RemoveUploadAction(ActionEvent event){
            this.PhotoUrl = "";
            updateImageView("");
    }
   void updateImageView(String url){
      if(url!=""){
          ImageViewCover.setImage(new Image(url));
      }else{
          ImageViewCover.setImage(null);
      }

   }
}

/**************************************************************************
 * (C) Copyright 1992-2018 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/
