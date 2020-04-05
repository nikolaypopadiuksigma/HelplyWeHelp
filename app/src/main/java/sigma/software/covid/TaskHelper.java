package sigma.software.covid;

public class TaskHelper {
    private boolean isClose;
    private String phone;
    private String location;
    private String message;
    private String helperId;
    private String taskId;

    public TaskHelper(boolean isClose, String phone, String location, String message, String helperId, String taskId) {
        this.isClose = isClose;
        this.phone = phone;
        this.location = location;
        this.message = message;
        this.helperId = helperId;
        this.taskId = taskId;
    }

    public boolean isClose() {
        return isClose;
    }

    public String getPhone() {
        return phone;
    }

    public String getLocation() {
        return location;
    }

    public String getMessage() {
        return message;
    }

    public String getHelperId() {
        return helperId;
    }

    public String getTaskId() {
        return taskId;
    }
}
