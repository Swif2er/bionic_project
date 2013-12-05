package edu.bionic.client.GUI;

import java.util.ArrayList;
import java.util.List;

import org.moxieapps.gwt.uploader.client.Uploader;
import org.moxieapps.gwt.uploader.client.events.FileDialogCompleteEvent;
import org.moxieapps.gwt.uploader.client.events.FileDialogCompleteHandler;
import org.moxieapps.gwt.uploader.client.events.UploadErrorEvent;
import org.moxieapps.gwt.uploader.client.events.UploadErrorHandler;
import org.moxieapps.gwt.uploader.client.events.UploadSuccessEvent;
import org.moxieapps.gwt.uploader.client.events.UploadSuccessHandler;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.Label;

import edu.bionic.client.Audio.SoundPlayer;
import edu.bionic.client.Util.ScheduledRun;

public class UserInterface extends Composite {

	private VerticalPanel vPanel = new VerticalPanel();
	private HorizontalPanel menuPanel = new HorizontalPanel();
	private static List<HorizontalPanel> tracks = new ArrayList<HorizontalPanel>();
	//private static List<String> data = new ArrayList<String>();
	private int countTracks = 0;

	private Uploader uploadBtn;

	public UserInterface() {
		initWidget(vPanel);
		// vPanel.setSpacing(20);

		//adding title
		Label label1 = new Label("beat");
		label1.addStyleName("label1");
		Label label2 = new Label("maker");
		label2.addStyleName("label2");
		HorizontalPanel titlePanel = new HorizontalPanel();
		//titlePanel.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		titlePanel.add(label1);
		titlePanel.add(label2);
		vPanel.add(titlePanel);
		
		//adding Menu Panel
		vPanel.add(menuPanel);
		menuPanel.setHeight("40px");
		//menuPanel.addStyleName("topPanel");
		menuPanel.setSpacing(2);

		Button addTrackBtn = new Button("add track");
		addTrackBtn.setTitle("Add track row");
		addTrackBtn.removeStyleName("gwt-Button");
		addTrackBtn.addStyleName("button");
		addTrackBtn.addStyleName("addTrackButton");
		addTrackBtn.addClickHandler(new addTrackClickHandler());
		menuPanel.add(addTrackBtn);

		HorizontalPanel splitter = new HorizontalPanel();
		splitter.setWidth("179px");
		menuPanel.add(splitter);

		Button playBtn = new Button("A");
		playBtn.setTitle("Start playing");
		playBtn.addStyleName("button");
		playBtn.addStyleName("controlButton");
		playBtn.addClickHandler(new playClickHandler());
		// playBtn.addKeyDownHandler(new spaceKeyDownHandler());
		menuPanel.add(playBtn);

		Button stopBtn = new Button("F");
		stopBtn.setTitle("Stop playing");
		stopBtn.addStyleName("button");
		stopBtn.addStyleName("controlButton");
		stopBtn.addClickHandler(new stopClickHandler());
		menuPanel.add(stopBtn);

		//adding default tracks
		// Load initial samples
		addTrack();
		// Uploader x = (Uploader) tracks.get(0).getWidget(0);
		// x.

	}

	private void addTrack() {
		int numTracks = getTracks().size();
		getTracks().add(new HorizontalPanel());
		HorizontalPanel hPanel = getTracks().get(numTracks);
		vPanel.add(hPanel);

		// create and add Uploader button
		hPanel.setSpacing(2);
		hPanel.add(createUploader("set sample...")); // + countTracks));

		// create and add sample Toggle button
		for (int i = 0; i < 16; i++) {
			ToggleButton toggle = new ToggleButton(new Image("/Images/upImage.gif"), new Image("/Images/downImage.gif")); 
			if (i % 4 == 0) {
				toggle.addStyleName("toggle"); 
			}
				hPanel.add(toggle);
		}

		// create and add RemoveTrack button
		Button removeTrackBtn = new Button("remove");
		removeTrackBtn.setTitle("Remove this track");
		removeTrackBtn.setStylePrimaryName("button");
		removeTrackBtn.addStyleName("removeTrackButton");
		removeTrackBtn.addStyleName("active");
		removeTrackBtn.addClickHandler(new removeTrackClickHandler());
		hPanel.add(removeTrackBtn);

		lastRemoveBtnController();

		// System.out.println("Track " + countTracks++ + " added");
	}

	private Uploader createUploader(String bText) {
		Uploader uploader = new Uploader();
		uploader.setUploadURL("/FileUploadServlet").setButtonText(bText).setButtonWidth(118)
				.setButtonHeight(23).setButtonCursor(Uploader.Cursor.HAND)
				.setButtonAction(Uploader.ButtonAction.SELECT_FILE)
				.setTitle("Upload a sound file");
		uploader.setUploadSuccessHandler(new uploadSuccessHandler());
		uploader.setFileDialogCompleteHandler(new fileDialogCompleteHandler());
		uploader.setUploadErrorHandler(new uploadErrorHandler());
		uploader.setStyleName("uploadButton");
		uploader.addStyleName("button");
		uploader.addDomHandler(new uploadClickHandler(), ClickEvent.getType());
		//uploader.setFileDialogStartHandler(new fileDialogStartHandler());
		return uploader;
	}

	private class uploadClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			uploadBtn = (Uploader) event.getSource();
			System.out.println("uploadClickHandler");
//			for (HorizontalPanel panel : tracks) {
//				if (panel.getWidget(0) == uploadBtn) {
//					((Uploader) uploadBtn).setButtonText("set sample " + tracks.indexOf(panel));
//				}
//			}
		}
	}

	private class uploadSuccessHandler implements UploadSuccessHandler {
		public boolean onUploadSuccess(UploadSuccessEvent uploadSuccessEvent) {
			//TODO:check which uploader.getText or title is "set sample + id" and set sample there
			String fileName = uploadSuccessEvent.getFile().getName();
//			for (HorizontalPanel panel : tracks) {
//				if (fileName == ((Button) panel.getWidget(0)).getText()) {
//					Uploader uploadBtn = (Uploader) panel.getWidget(0);
					uploadBtn.setButtonText(fileName.replaceAll("\\.\\w+", ""));
					uploadBtn.setTitle("change sample");
					// Window.alert(uploadSuccessEvent.getFile().getName());
//					for (int i = 0; i < tracks.size(); i++) {
//						if (uploadBtn.getParent() == tracks.get(i)) {
//							ScheduledRun.players.add(new SoundPlayer("UserSoundClips/" + fileName));
//						}
//					}
					return true;
//				}
//			}
		}
	}

	private class fileDialogCompleteHandler implements FileDialogCompleteHandler {
		public boolean onFileDialogComplete(FileDialogCompleteEvent dialogCompleteEvent) {
			if (dialogCompleteEvent.getTotalFilesInQueue() > 0) {
				uploadBtn.setButtonText("<span class=\"buttonText\">uploading...</span>");
				uploadBtn.startUpload();
			}
			return true;
		}
	}

	private class uploadErrorHandler implements UploadErrorHandler {
		public boolean onUploadError(UploadErrorEvent uploadErrorEvent) {
			Window.alert("Upload of file " + uploadErrorEvent.getFile().getName()
					+ " failed due to [" + uploadErrorEvent.getErrorCode().toString() + "]: "
					+ uploadErrorEvent.getMessage());
			// uploader.setButtonText("set sample " + (countTracks - 1));
			uploadBtn.setButtonText("upload failed");
			return true;
		}
	}

	private class playClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			new ScheduledRun();

			// for Pause Event
			//
		}
	}

	// private class spaceKeyDownHandler implements KeyDownHandler {
	//
	// @Override
	// public void onKeyDown(KeyDownEvent event) {
	// if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// }
	//
	// }

	private class stopClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			ScheduledRun.setStop(true);
			// ScheduledRun.togBtnIndex = 1;
		}

	}

	private class addTrackClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			addTrack();
		}
	}

	private class removeTrackClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			Widget remover = (Widget) event.getSource();
			for (int i = 0; i < tracks.size(); i++) {
				if (remover == tracks.get(i).getWidget(17)) {
					// System.out.println("id: " + i);
					removeTrack(i);
					// TODO: remove player from 'players' list;
					lastRemoveBtnController();
					break;
				}
			}
		}
	}

	private void removeTrack(int id) {
		System.out.println("removing track " + id);
		tracks.get(id).removeFromParent();
		System.out.println("tracks, before removing: " + tracks.size());
		tracks.remove(id);
		System.out.println("tracks, after removing: " + tracks.size());
	}

	public static List<HorizontalPanel> getTracks() {
		return tracks;
	}

	private void lastRemoveBtnController() {
		Button remove = (Button) tracks.get(0).getWidget(17);
		if (tracks.size() == 1) {
			remove.setEnabled(false);
			remove.removeStyleName("active");
			remove.setTitle("Can't remove this track");
			// remove.setStyleName("removeTrackButton-disabled");
		} else if (remove.isEnabled() == false) {
			remove.setEnabled(true);
			remove.addStyleName("active");
			remove.setTitle("Remove this track");
			// remove.setStyleName("removeTrackButton");
		}
		remove.addStyleName("button");
	}

//	public int getTrackIdByUploadButton(Uploader uploader) {
//		// TODO
//		return 0;
//	}
//
//	public void getUploaderByEvent() {
//
//	}
}
