package sa.aqarz.Modules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MsgModules {


    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("parent_id")
    @Expose
    private Object parentId;
    @SerializedName("sender_id")
    @Expose
    private Object senderId;
    @SerializedName("receiver_id")
    @Expose
    private Integer receiverId;
    @SerializedName("sender_name")
    @Expose
    private Object senderName;
    @SerializedName("sender_photo")
    @Expose
    private Object senderPhoto;
    @SerializedName("receiver_name")
    @Expose
    private String receiverName;
    @SerializedName("msg_id")
    @Expose
    private String msg_id;
    @SerializedName("receiver_photo")
    @Expose
    private String receiverPhoto;
    @SerializedName("parent_body")
    @Expose
    private Object parentBody;
    @SerializedName("parent_title")
    @Expose
    private Object parentTitle;
    @SerializedName("parent_created_at")
    @Expose
    private Object parentCreatedAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("form_me")
    @Expose
    private Integer formMe;


    public MsgModules(String body, int formMe) {
        this.body = body;
        this.formMe = formMe;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Object getParentId() {
        return parentId;
    }

    public String getMsg_id() {
        return msg_id;
    }

    public void setParentId(Object parentId) {
        this.parentId = parentId;
    }

    public Object getSenderId() {
        return senderId;
    }

    public void setSenderId(Object senderId) {
        this.senderId = senderId;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public Object getSenderName() {
        return senderName;
    }

    public void setSenderName(Object senderName) {
        this.senderName = senderName;
    }

    public Object getSenderPhoto() {
        return senderPhoto;
    }

    public void setSenderPhoto(Object senderPhoto) {
        this.senderPhoto = senderPhoto;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhoto() {
        return receiverPhoto;
    }

    public void setReceiverPhoto(String receiverPhoto) {
        this.receiverPhoto = receiverPhoto;
    }

    public Object getParentBody() {
        return parentBody;
    }

    public void setParentBody(Object parentBody) {
        this.parentBody = parentBody;
    }

    public Object getParentTitle() {
        return parentTitle;
    }

    public void setParentTitle(Object parentTitle) {
        this.parentTitle = parentTitle;
    }

    public Object getParentCreatedAt() {
        return parentCreatedAt;
    }

    public void setParentCreatedAt(Object parentCreatedAt) {
        this.parentCreatedAt = parentCreatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getFormMe() {
        return formMe;
    }

    public void setFormMe(Integer formMe) {
        this.formMe = formMe;
    }

}
