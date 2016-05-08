/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package symmetricgroup.chesswars.battle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import symmetricgroup.chesswars.battle.move.Move;
import symmetricgroup.chesswars.map.BattleMap;
import symmetricgroup.chesswars.map.MapParser;
import symmetricgroup.chesswars.players.ArmyColor;
import symmetricgroup.chesswars.players.Player;
import symmetricgroup.chesswars.players.ai.*;
import symmetricgroup.chesswars.players.ui.UserControl;
import symmetricgroup.chesswars.players.ui.UserPlayer;
/**
 * BattleIO on luokka, jonka avulla Battle-olio voidaan tallentaa tiedostoon ja lukea tiedostosta.
 */
public class BattleIO {
    
    /**
     * Metodi tallentaa taistelun tekstitiedostoon.
     * @param filename tallennettavan tekstitiedoston nimi
     * @param battle tallenettava taistelu
     */
    public static void saveBattle(String filename, Battle battle) {
       
        String battleConf = BattleParser.battleToString(battle);
        
        writeBattleConf(filename, battleConf);
        
    
    }
    
    /**
     * Metodi lukee taistelun tiedostosta.
     * @param filename tiedoston nimi, josta taistelu halutaan lukea.
     * @param control käytttäjien luomiseen ja ohjaamiseen tarkoitettu olio
     * @return palauttaa tiedostosta luetun taistelun
     */
    public static Battle readBattle(String filename, UserControl control) {

        try  {
            File file = new File(filename);
            
            BufferedReader reader = new BufferedReader(new FileReader(file));

            
            String battleConf = reader.readLine();
            
            reader.close();

            return BattleParser.stringToBattle(battleConf, control);
            
        } catch (Exception e) { }
        
        return null;
    }
    
    private static void writeBattleConf(String filename, String battleConf) {
        
            
        try (FileWriter writer = new FileWriter(filename)) {
            
            writer.append(battleConf);
            
        } catch (IOException e) {
        
        }
    }
    
}
