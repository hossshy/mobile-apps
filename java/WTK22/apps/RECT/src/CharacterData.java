public class CharacterData
{
	static final int ATTR_SHOW_NORMAL = 10;
	static final int ATTR_SHOW_ALL = 11;
	static final int ATTR_SHOW_DARKONLY = 12;
	static final int ATTR_SHOW_MYITEM = 13;
	static final int ATTR_SHOW_MYITEM_DETAIL = 14;
	static final int ATTR_SHOW_FLAG = 15;
	
	static final int STATE_SHOW = 1;
	static final int STATE_DISABLE = 2;

	int x, y, width, height;
	int state;
	int attribute;
	int speed = 0;
	int id;
	int imageId;
	int wallId;
	int rootId = -1;

	public CharacterData(int x, int y, int width, int height, int imageId)
	{
		this(x, y, width, height, imageId, 0, ATTR_SHOW_NORMAL, STATE_SHOW, imageId);
	}
	
	public CharacterData(int x, int y, int width, int height, int imageId, int wallId, int attribute, int state, int id)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.imageId = imageId;
		this.wallId = wallId;
		this.attribute = attribute;
		this.state = state;
		this.id = id;
	}

	public String toSaveData()
	{
		return id + ":" + state;
	}

	public boolean isClick(CharacterData item)
	{
		return (x >= item.x) &&
			(x <= (item.x + item.width)) &&
			(y >= item.y) &&
			(y <= (item.y + item.height));
	}
}
