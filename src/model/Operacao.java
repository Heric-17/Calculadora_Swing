package model;

public class Operacao {

	public static String verifica(String resultado) {

		String arrumarString = resultado;

		if (arrumarString.contains(".")) {
			int casaVirg = arrumarString.indexOf(".");
			String confere = arrumarString.substring(casaVirg + 1);

			for (int i = 0; i < confere.length(); i++) {
				if (!Character.toString(confere.toCharArray()[i]).equalsIgnoreCase("0")) {
					i = confere.length() + 1;
				} else {
					arrumarString = arrumarString.substring(0, casaVirg);
				}
			}
		}
		return arrumarString;
	}
}
