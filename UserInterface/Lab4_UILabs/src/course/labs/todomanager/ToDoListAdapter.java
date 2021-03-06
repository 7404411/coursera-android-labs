package course.labs.todomanager;

import java.util.ArrayList;
import java.util.List;

import course.labs.todomanager.ToDoItem.Status;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ToDoListAdapter extends BaseAdapter {

	private final List<ToDoItem> mItems = new ArrayList<ToDoItem>();
	private final Context mContext;
	private static LayoutInflater inflater;

	private static final String TAG = "Lab-UserInterface";

	public ToDoListAdapter(Context context) {

		mContext = context;

	}

	// Add a ToDoItem to the adapter
	// Notify observers that the data set has changed

	public void add(ToDoItem item) {

		mItems.add(item);
		notifyDataSetChanged();

	}

	// Clears the list adapter of all items.

	public void clear() {

		mItems.clear();
		notifyDataSetChanged();

	}

	// Returns the number of ToDoItems

	@Override
	public int getCount() {

		return mItems.size();

	}

	// Retrieve the number of ToDoItems

	@Override
	public Object getItem(int pos) {

		return mItems.get(pos);

	}

	// Get the ID for the ToDoItem
	// In this case it's just the position

	@Override
	public long getItemId(int pos) {

		return pos;

	}

	// Create a View for the ToDoItem at specified position
	// Remember to check whether convertView holds an already allocated View
	// before created a new View.
	// Consider using the ViewHolder pattern to make scrolling more efficient
	// See:
	// http://developer.android.com/training/improving-layouts/smooth-scrolling.html

	private static class Holder {
		public TextView titleView;
		public CheckBox checkbox;
		public TextView priorityView;
		public TextView dateView;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;

		// TODO - Get the current ToDoItem
		final ToDoItem toDoItem = mItems.get(position);
		View itemLayout;
		if (rowView == null) {
			inflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			itemLayout = inflater.inflate(R.layout.todo_item, null);
			final Holder holder = new Holder();
			holder.titleView = (TextView) itemLayout.findViewById(R.id.titleView);
			holder.checkbox = (CheckBox) itemLayout.findViewById(R.id.statusCheckBox);
			holder.priorityView = (TextView) itemLayout.findViewById(R.id.priorityView);
			holder.dateView = (TextView) itemLayout.findViewById(R.id.dateView);
			holder.titleView.setText(toDoItem.getTitle().toString());
			if (toDoItem.getStatus() == Status.DONE) {
				holder.checkbox.setChecked(true);
			} else {
				holder.checkbox.setChecked(false);
			}
			holder.priorityView.setText(toDoItem.getPriority().toString());
			holder.dateView.setText(ToDoItem.FORMAT.format(toDoItem.getDate()));



			itemLayout.setTag(holder);
		} else {
			itemLayout = convertView;
			
		}
		
		Holder holder = (Holder) itemLayout.getTag();
			// Return the View you just created
		// TODO - Display Priority in a TextView
		final TextView priorityView = holder.priorityView;
		
		// TODO - Display Time and Date.
		// Hint - use ToDoItem.FORMAT.format(toDoItem.getDate()) to get date
		// and
		// time String
		final TextView dateView = holder.dateView;
// TODO - Must also set up an OnCheckedChangeListener,
// which is called when the user toggles the status checkbox
		
// TODO - Inflate the View for this ToDoItem
// from todo_item.xml
// Fill in specific ToDoItem data
// Remember that the data that goes in this View
// corresponds to the user interface elements defined
// in the layout file
		
// TODO - Display Title in TextView
		final TextView titleView = holder.titleView;
		
// TODO - Set up Status CheckBox
		final CheckBox statusView = holder.checkbox;
		statusView
		.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				buttonView.toggle();
			}
		});
			return itemLayout;
		
		}
	}

