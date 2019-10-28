package Cliente;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author gabriel
 */
public class Player extends JLabel {
    public int x = 0, y = 0, vida = 100;
    ImageIcon walkL;
    ImageIcon walkR;
    ImageIcon walkU;
    ImageIcon walkD;
    ImageIcon stopped;
    ImageIcon stoppedL;
    ImageIcon attack;
    ImageIcon attackL = null;
    ImageIcon UltimaIMG = null;


    public void setup() {
        setText("12");
        walkR = new ImageIcon(
                new ImageIcon(getClass()
                        .getResource("c_d.gif"))
                        .getImage()
                        .getScaledInstance(88, 127, Image.SCALE_DEFAULT));
        walkL = new ImageIcon(
                new ImageIcon(getClass()
                        .getResource("c_d-reverse.gif"))
                        .getImage()
                        .getScaledInstance(88, 127, Image.SCALE_DEFAULT));
        attack = new ImageIcon(
                new ImageIcon(getClass()
                        .getResource("soco.gif"))
                        .getImage()
                        .getScaledInstance(88, 127, Image.SCALE_DEFAULT));
        attackL = new ImageIcon(
                new ImageIcon(getClass()
                        .getResource("soco-reverse.gif"))
                        .getImage()
                        .getScaledInstance(88, 127, Image.SCALE_DEFAULT));
        stoppedL = new ImageIcon(
                new ImageIcon(getClass()
                        .getResource("p_d-reverse.gif"))
                        .getImage()
                        .getScaledInstance(88, 127, Image.SCALE_DEFAULT));
        stopped = new ImageIcon(
                new ImageIcon(getClass()
                        .getResource("p_d.gif"))
                        .getImage()
                        .getScaledInstance(88, 127, Image.SCALE_DEFAULT));
        setBounds(x, y, 90, 127);
    }

    public void setIconRight() {
        setIcon(walkR);
        this.UltimaIMG = walkR;
    }

    public void setIconLeft() {
        setIcon(walkL);
        this.UltimaIMG = walkL;
    }

    public void setIconSpace() {
        if (UltimaIMG == walkR) {
            setIcon(attack);
        } else {
            setIcon(attackL);
        }

    }

    public void setIconStopped() {
        if (this.UltimaIMG == walkR) {
            setIcon(stopped);
        } else {
            setIcon(stoppedL);
        }

    }
}
