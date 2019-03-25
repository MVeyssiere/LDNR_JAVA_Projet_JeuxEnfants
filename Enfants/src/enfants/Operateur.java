/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package enfants;

/**
 *
 * @author Thomas
 */
/** Représente un opérateur arithmétique.
 */
public enum Operateur
{
	ADDITION {
		public String toString() {
			return "+";
		}
	},
	SOUSTRACTION {
		public String toString() {
			return "-";
		}
	},
	MULTIPLICATION {
		public String toString() {
			return "x";
		}
	},
	DIVISION {
		public String toString() {
			return "÷";
		}
	}
}