package byog.lab5;

import java.awt.Dimension;
import java.awt.Toolkit;

public class GetSreenSize {
    public static void main(String[] args){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        System.out.println(dimension.height);
        System.out.println(dimension.width);
    }

}
