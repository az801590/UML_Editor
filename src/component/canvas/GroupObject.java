package component.canvas;

import java.util.HashSet;

public class GroupObject implements Group {
	private GroupObject group = null;
	HashSet<Group> groupSet = new HashSet<Group>();

	public void addGroupList(Group obj) {
		groupSet.add(obj);
	}

	public void setChildParentLink() {
		for (Group g : groupSet) {
			if (g.getGroupRoot() != this) {
				g.setGroupParent(this);
			}
		}
	}
	
	public void unsetChildParentLink() {
		for(Group g : groupSet) {
			if(g.getGroupRoot() == this) {
				g.setGroupParent(null);
			}
		}
	}

	@Override
	public Group getGroupRoot() {
		if (group != null) {
			return group.getGroupRoot();
		} else {
			return this;
		}
	}

	@Override
	public void showPorts(boolean stat) {
		for (Group tmp : groupSet) {
			tmp.showPorts(stat);
		}
	}

	@Override
	public void moveObject(int position[]) {
		for (Group g : groupSet) {
			g.moveObject(position);
		}
	}

	@Override
	public void setGroupParent(GroupObject g) {
		this.group = g;
	}
}
