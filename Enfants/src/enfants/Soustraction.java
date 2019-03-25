/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package enfants;

import java.util.Random;

/**
 *
 * @author Thomas
 */
public class Soustraction {
    
    
    
    
    
    public int genererSoustraction()
	{
		
	Random r = new Random();
        int a = r.nextInt(10);
        int b = r.nextInt(10);
        int res = a-b;
        if (b>a){
            genererSoustraction();
        }
        return res;

	}

}
