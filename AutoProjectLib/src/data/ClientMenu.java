package data;

import java.util.Scanner;

public class ClientMenu {
	public static Auto selectionsMenu(Auto a) {
		boolean loop = true;
		Scanner s = new Scanner(System.in);
		do {
			int i = 0;
			int selnum = 0;
			for (OptionSet os : a.getOptions()) {
				System.out.printf("%d, %s", i++, os.getName());
			}
			selnum = s.nextInt();
			if (selnum == 0)
				break;
			OptionSet selected = a.getOptions().get(selnum);
			i = 0;
			for (Option o : selected.getOptions()) {
				System.out.printf("%d, %s", i++, o.getTitle());
			}
			selnum = s.nextInt();
			if (selnum == 0)
				break;
			a.addSelection(selected, selected.getOptions().get(selnum));
		} while (loop == true);
		s.close();
		return a;
	}
}
