
package edu.bionic.client.Util;  
  
import com.google.gwt.i18n.client.NumberFormat;  
import com.google.gwt.user.client.Window;  
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.HorizontalPanel;  
import com.google.gwt.user.client.ui.Label;  
import com.google.gwt.user.client.ui.RootPanel;  
import com.google.gwt.user.client.ui.VerticalPanel;  
import org.moxieapps.gwt.uploader.client.Uploader;  
import org.moxieapps.gwt.uploader.client.events.*;  

  
public class UploadButton {  
  
    private Label progressLabel;  
    private Uploader uploader;  
    FileUpload file;
    
    public void onModuleLoad() {  
        progressLabel = new Label();  
        progressLabel.setStyleName("progressLabel");  
  
        uploader = new Uploader();  
        uploader.setUploadURL("/DevNullUploadServlet")  
            .setButtonText("<span class=\"buttonText\">Click to Upload</span>")  
            .setButtonTextStyle(".buttonText {font-family: Arial, sans-serif; font-size: 14px; color: #BB4B44}")  
            .setFileSizeLimit("50 MB")  
            .setButtonWidth(150)  
            .setButtonHeight(22)  
            .setButtonCursor(Uploader.Cursor.HAND)  
            .setButtonAction(Uploader.ButtonAction.SELECT_FILE)  
            .setUploadProgressHandler(new UploadProgressHandler() {  
                public boolean onUploadProgress(UploadProgressEvent uploadProgressEvent) {  
                    progressLabel.setText(NumberFormat.getPercentFormat().format(  
                        (double)uploadProgressEvent.getBytesComplete() / (double)uploadProgressEvent.getBytesTotal()  
                    ));  
                    return true;  
                }  
            })  
            .setUploadSuccessHandler(new UploadSuccessHandler() {  
                public boolean onUploadSuccess(UploadSuccessEvent uploadSuccessEvent) {  
                    resetText();  
                    StringBuilder sb = new StringBuilder();  
                    sb.append("File ").append(uploadSuccessEvent.getFile().getName())  
                        .append(" (")  
                        .append(NumberFormat.getDecimalFormat().format(uploadSuccessEvent.getFile().getSize() / 1024))  
                        .append(" KB)")  
                        .append(" uploaded successfully at ")  
                        .append(NumberFormat.getDecimalFormat().format(  
                            uploadSuccessEvent.getFile().getAverageSpeed() / 1024  
                        ))  
                        .append(" Kb/second");  
                    progressLabel.setText(sb.toString());  
                    return true;  
                }  
            })  
            .setFileDialogCompleteHandler(new FileDialogCompleteHandler() {  
                public boolean onFileDialogComplete(FileDialogCompleteEvent fileDialogCompleteEvent) {  
                    if (fileDialogCompleteEvent.getTotalFilesInQueue() > 0 && uploader.getStats().getUploadsInProgress() <= 0) {  
                        progressLabel.setText("0%");  
                        uploader.setButtonText("<span class=\"buttonText\">Uploading...</span>");  
                        uploader.startUpload();  
                    }  
                    return true;  
                }  
            })  
            .setFileQueueErrorHandler(new FileQueueErrorHandler() {  
                public boolean onFileQueueError(FileQueueErrorEvent fileQueueErrorEvent) {  
                    resetText();  
                    Window.alert("Upload of file " + fileQueueErrorEvent.getFile().getName() + " failed due to [" +  
                        fileQueueErrorEvent.getErrorCode().toString() + "]: " + fileQueueErrorEvent.getMessage()  
                    );  
                    return true;  
                }  
            })  
            .setUploadErrorHandler(new UploadErrorHandler() {  
                public boolean onUploadError(UploadErrorEvent uploadErrorEvent) {  
                    resetText();  
                    Window.alert("Upload of file " + uploadErrorEvent.getFile().getName() + " failed due to [" +  
                        uploadErrorEvent.getErrorCode().toString() + "]: " + uploadErrorEvent.getMessage()  
                    );  
                    return true;  
                }  
            });  
  
        VerticalPanel verticalPanel = new VerticalPanel();  
        verticalPanel.add(uploader);  
        verticalPanel.add(progressLabel);  
        verticalPanel.setCellHorizontalAlignment(uploader, HorizontalPanel.ALIGN_LEFT);  
        verticalPanel.setCellHorizontalAlignment(progressLabel, HorizontalPanel.ALIGN_LEFT);  
  
        //noinspection GwtToHtmlReferences  
        RootPanel.get("TextButtonAndProgressText").add(verticalPanel);  
    }  
  
    private void resetText() {  
        progressLabel.setText("");  
        uploader.setButtonText("<span class=\"buttonText\">Click to Upload</span>");  
    }  
}  