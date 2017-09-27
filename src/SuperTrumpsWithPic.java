import javax.swing.*;

public class SuperTrumpsWithPic extends SuperTrumps {
    private ImageIcon imageCard;

    SuperTrumpsWithPic(String n, ImageIcon i){
        super(n);
        imageCard = i;
    }

    public ImageIcon getImageCard() {
        return imageCard;
    }
}
