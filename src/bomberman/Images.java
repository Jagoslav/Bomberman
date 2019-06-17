/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * klasa posiada pola trzymające poszczególne obrazki używane w grze.
 * XYZImage - obrazek reprezentujący XYZ
 * @author Jakub
 */
public class Images {
        public Image backgroundImage = new ImageIcon("src\\images\\background.png").getImage();
        public Image wallImage = new ImageIcon("src\\images\\wall.png").getImage();
        public Image bombImage = new ImageIcon("src\\images\\bomb.png").getImage();
        public Image playerImage = new ImageIcon("src\\images\\player.png").getImage();
        public Image player2Image = new ImageIcon("src\\images\\player2.png").getImage();
        public Image botImage = new ImageIcon("src\\images\\bot.png").getImage();
        public Image crateImage = new ImageIcon("src\\images\\crate.png").getImage();
        public Image button = new ImageIcon("src\\images\\button.png").getImage();
        public Image logo = new ImageIcon("src\\images\\logo.png").getImage();
        public Image exitField = new ImageIcon("src\\images\\exitField.png").getImage();
        public Image explosionCenter = new ImageIcon("src\\images\\explosionCenter.png").getImage();
        public Image explosionMiddleHorizontal = new ImageIcon("src\\images\\explosionMiddleHorizontal.png").getImage();
        public Image explosionMiddleVertical = new ImageIcon("src\\images\\explosionMiddleVertical.png").getImage();
        public Image explosionEndLeft = new ImageIcon("src\\images\\explosionEndLeft.png").getImage();
        public Image explosionEndRight = new ImageIcon("src\\images\\explosionEndRight.png").getImage();
        public Image explosionEndDown = new ImageIcon("src\\images\\explosionEndDown.png").getImage();
        public Image explosionEndUp = new ImageIcon("src\\images\\explosionEndUp.png").getImage();
        public Image explosionSizeUp = new ImageIcon("src\\images\\explosionSizeUp.png").getImage();
        public Image bombNumberUp = new ImageIcon("src\\images\\bombNumberUp.png").getImage();
        public Image movementSpeedUp = new ImageIcon("src\\images\\movementSpeedUp.png").getImage();
        public Image hud = new ImageIcon("src\\images\\hud.png").getImage();
}
