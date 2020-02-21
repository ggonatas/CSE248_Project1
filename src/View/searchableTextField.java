package View;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.*;

public class searchableTextField extends TextField
{

    private final SortedSet<String> entries;
    private ContextMenu entriesPopup;

    public searchableTextField(ArrayList<String> list) {
        super();
        entries = new TreeSet<String>(list);
        entriesPopup = new ContextMenu();

        textProperty().addListener((observableValue, s, s2) -> {
            if (getText().length() == 0)
            {
                entriesPopup.hide();
            } else if(getText()!=null)
            {
                LinkedList<String> searchResult = new LinkedList<>();
                String[] input = entries.toArray(new String[entries.size()]);
                for(int i=0;i<input.length;i++){
                    if(input[i].toLowerCase().contains(getText().toLowerCase())){
                        searchResult.add(input[i]);
                    }
                }
                if (entries.size() > 0)
                {
                    populatePopup(searchResult);
                    if (!entriesPopup.isShowing())
                    {
                        entriesPopup.show(searchableTextField.this, Side.BOTTOM, 0, 0);
                    }
                } else
                {
                    entriesPopup.hide();
                }
            }
        });

        focusedProperty().addListener((observableValue, aBoolean, aBoolean2) -> entriesPopup.hide());

    }

    // Get the existing set of autocomplete entries.
    // return The existing autocomplete entries.
    public SortedSet<String> getEntries() { return entries; }

    //add search results to popup
    private void populatePopup(List<String> searchResult) {
        List<CustomMenuItem> menuItems = new LinkedList<>();
        // If you'd like more entries, modify this line.
        int maxEntries = 10;
        int count = Math.min(searchResult.size(), maxEntries);
        for (int i = 0; i < count; i++)
        {
            final String result = searchResult.get(i);
            Label entryLabel = new Label(result);
            CustomMenuItem item = new CustomMenuItem(entryLabel, true);
            item.setOnAction(actionEvent -> {
                setText(result);
                entriesPopup.hide();
            });
            menuItems.add(item);
        }
        entriesPopup.getItems().clear();
        entriesPopup.getItems().addAll(menuItems);

    }
}