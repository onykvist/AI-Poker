package poker_joko;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Card2Icon {

	public Icon getIcon(int card) {
		
		String fileName = "/res/80px-Playing_card_";
		
		String color = "spade_";
		if(card < 200){
			color = "heart_";
			card -= 100;
		}
		else if(card < 300 ){
			color = "diamond_";
			card -= 200;
		}
		else if(card < 400 ){
			color = "club_";
			card -= 300;
		} else{
			card -= 400;
		}
		
		String value = "";
		
		if(card < 11 && card > 1){
			value = Integer.toString(card);
		}
		else if(card == 1){
			value = "A";
		}
		else if(card == 11){
			value = "J";
		}
		else if(card == 12){
			value = "Q";
		}
		else if(card == 13){
			value = "K";
		}
		
		fileName = fileName + color + value + ".png";
		
		System.out.println(fileName);
		
		URL test = getClass().getResource(fileName);
		File file = new File(test.getFile().replace("%20", " "));
		// Read file and convert it to icon
		Image image = null;
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			System.out.println("Error on reading image file");
		}
		Icon icon = new ImageIcon(image);
		return icon;
	}

}
