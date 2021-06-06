package component.canvas;

public interface Group {
	public Group getGroupRoot();
	public void showPorts(boolean stat);
	public void moveObject(int position[]);
	public void setGroupParent(GroupObject g);
	public boolean isGroupObject();
}
