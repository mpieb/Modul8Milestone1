package com.video.view;

import java.util.Date;
import java.util.*;

import javax.swing.JOptionPane;

import com.video.application.VideoController;
import com.video.domain.Tag;
import com.video.domain.User;
import com.video.domain.Video;
import com.video.exceptions.EmptyFieldsException;
import com.video.exceptions.NoVideoIncludedException;

public class AppVideo {

	private static VideoController controller = new VideoController();

	public static void main(String[] args) throws EmptyFieldsException {

		String nom, cognom, password;
		User user;
		do {
			try {
				JOptionPane.showMessageDialog(null, "Entrada!", "Llista de vídeos", JOptionPane.INFORMATION_MESSAGE);
				nom = JOptionPane.showInputDialog(null, "Escriu el teu nom", "NOM",
						JOptionPane.QUESTION_MESSAGE);
				cognom = JOptionPane.showInputDialog(null, "Escriu el cognom", "COGNOM",
						JOptionPane.QUESTION_MESSAGE);
				password = JOptionPane.showInputDialog(null, "Escriu el password", "PASSWORD",
						JOptionPane.QUESTION_MESSAGE);

				user = new User(nom, cognom, password, new Date());
				JOptionPane.showMessageDialog(null, "Hola " + user.getName() + "!", "Benvingut...",
						JOptionPane.INFORMATION_MESSAGE);

				if (nom.equals("") || cognom.equals("") || password.equals("")) {
					JOptionPane.showMessageDialog(null,
							"Nom, Cognom i Password no poden ser camps buits!", "ERROR_MESSAGE",
							JOptionPane.ERROR_MESSAGE);
					throw new EmptyFieldsException();

				}
			} catch (EmptyFieldsException e) {
				e.printStackTrace();
				nom = "";
				cognom = "";
				password = "";
				user = null;
			}
		} while (nom.equals("") || cognom.equals("") || password.equals(""));

		int opcion;

		do {
			
			try {
			opcion = Integer.parseInt(JOptionPane.showInputDialog(null,
					"¿Quina acció vols fer? \n1. Pujar vídeo \n2. Veure vídeos\n3. Sortir", "",
					JOptionPane.QUESTION_MESSAGE));

			switch (opcion) {

			case 1:
				JOptionPane.showMessageDialog(null, "Pujem un vídeo!", "UPLOAD",
						JOptionPane.INFORMATION_MESSAGE);
				JOptionPane.showMessageDialog(null,
						"Per pujar un vídeo: \n - URL del vídeo\n - Títol del vídeo\n - Una llista de TAGs",
						"UPLOAD", JOptionPane.INFORMATION_MESSAGE);
				String urlVideo, titolVideo;
				int numTags;
				do {

					try {
						urlVideo = JOptionPane.showInputDialog(null, "Escriu la URL: ", "URL",
								JOptionPane.QUESTION_MESSAGE);
						titolVideo = JOptionPane.showInputDialog(null, "Escriu el titol: ", "TITOL",
								JOptionPane.QUESTION_MESSAGE);
						numTags = Integer.parseInt(JOptionPane.showInputDialog(null,
								"¿Quantes TAGs vols?", "", JOptionPane.QUESTION_MESSAGE));
						if (urlVideo.equals("") || titolVideo.equals(""))
							throw new EmptyFieldsException();
						List<Tag> userTags = controller.addTag(numTags);
						JOptionPane.showMessageDialog(null, "Enllestit!", "", JOptionPane.INFORMATION_MESSAGE);
						try {
							controller.createVideo(urlVideo, titolVideo, user, userTags);
						} catch (NoVideoIncludedException e) {
							JOptionPane.showMessageDialog(null, "NO VIDEO INCLUDED", "ERROR",
									JOptionPane.ERROR_MESSAGE);
							e.printStackTrace();
						}
						if (urlVideo.equals("") || titolVideo.equals("") || numTags < 0) {
							JOptionPane.showMessageDialog(null, "SOME FIELDS ARE EMPTY", "ERROR",
									JOptionPane.ERROR_MESSAGE);
						}
						break;

					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null,
								"S´ha de introduir un nombre per els TAGs. Els caràcters "
										+ "A-Z o una cadena buida són incorrectes",
								"ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
						urlVideo = "";
						titolVideo = "";
						numTags = -1;
						break;
					} catch (EmptyFieldsException e) {
						JOptionPane.showMessageDialog(null, "URL i Titol no poden ser camps buits",
								"ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
						urlVideo = "";
						titolVideo = "";
						numTags = -1;
						break;
					}

				} while (urlVideo.equals("") || titolVideo.equals("") || numTags < 0);

				break;

			case 2:
				JOptionPane.showMessageDialog(null, "Aquestos són els vídeos!", "FIND VIDEO",
						JOptionPane.INFORMATION_MESSAGE);

				List<Video> videosForUser = controller.getUserVideoList(user);

				videosForUser.forEach((Video v) -> System.out.println(v.getTittle()));
				videosForUser.forEach((Video v) -> JOptionPane.showMessageDialog(null, "vídeos", "YOUR VIDEOS",
						JOptionPane.INFORMATION_MESSAGE));

				break;

			case 3:
				int resp = JOptionPane.showConfirmDialog(null, "Segur que vol sortir!", "Confirmació",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (resp == JOptionPane.YES_OPTION)
					break;
				opcion = 4; 

			default:
				break;
			}
			}catch(NumberFormatException e) {
				e.printStackTrace();
				  JOptionPane.showMessageDialog(null, "S´ha de triar almenys una opció!",
			                "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
				opcion =2;
			}
		} while (opcion != 3);

	}

}

    

