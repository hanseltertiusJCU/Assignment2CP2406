import javax.swing.*;

public class MineralWithPic extends Mineral {
    private ImageIcon imageCard;
    MineralWithPic(String n, float hard, float specGrav, String clea, String abu, String econValue, ImageIcon i){
        super(n,hard,specGrav,clea,abu,econValue);
        imageCard = i;
    }

    public ImageIcon getImageCard() {
        return imageCard;
    }
}
