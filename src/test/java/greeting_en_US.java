import java.util.ListResourceBundle;

public class greeting_en_US extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                {"hello", "Hey dude"},
                {"country", "United states"},
                {"currency", "dollars"}
        };
    }
}
