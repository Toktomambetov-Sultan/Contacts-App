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
import javafx.util.Callback;

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

   public void initialize() {
      // populate the ObservableList<Book>
      contacts.add(new Contact("Sultan",
         "Toktomambetov", "sultan.toktommabetov@gmail.com","996"));
//      books.add(new Book("C++ How to Program",
//         "/images/small/cpphtp.jpg", "/images/large/cpphtp.jpg"));
//      books.add(new Book("Internet and World Wide Web How to Program",
//         "/images/small/iw3htp.jpg", "/images/large/iw3htp.jpg"));
//      books.add(new Book("Java How to Program",
//         "/images/small/jhtp.jpg", "/images/large/jhtp.jpg"));
//      books.add(new Book("Visual Basic How to Program",
//         "/images/small/vbhtp.jpg", "/images/large/vbhtp.jpg"));
//      books.add(new Book("Visual C# How to Program",
//         "/images/small/vcshtp.jpg", "/images/large/vcshtp.jpg"));
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
                new Contact(FirstnameLabel.getText(),LastnameLabel.getText(),EmailLabel.getText(),PhoneNumberLabel.getText())
        );
   }

    @FXML
   void UpdateAction(ActionEvent event){
       Contact selectedItem = booksListView.getSelectionModel().getSelectedItem();
       selectedItem.setFirstname( FirstnameLabel.getText() );
       selectedItem.setLastname( LastnameLabel.getText() );
//       noteService.updateNode( selectedItem );

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
