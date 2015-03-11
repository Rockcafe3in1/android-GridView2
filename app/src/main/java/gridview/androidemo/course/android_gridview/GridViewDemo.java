package gridview.androidemo.course.android_gridview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;


public class GridViewDemo extends Activity
        implements AdapterView.OnItemSelectedListener, AbsListView.OnScrollListener,
        AdapterView.OnItemClickListener {

    private static final String TAG = "GridViewDemo";
    private GridView mGridView;
    private TextView mSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview_demo);

        // Get instance of TextView
        mSelection = (TextView) findViewById(R.id.selection);

        // Get instance of grid view
        mGridView = (GridView) findViewById(R.id.states_of_usa);

        // Bind to string array adapter
        // cell layout? seems R.layout.cell didn't work
        // Fixed with android.R.layout.simple.

        // Instead of simple_list_item, choose
        // simple_list_item_single_choice to show checkbox
        // per cell in grid view
        mGridView.setAdapter(
                new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item,
                        StatesOfUSA.states));

        // Didn't work!
        mGridView.setOnItemSelectedListener(this);

        // Caught click event instead of selection event
        mGridView.setOnItemClickListener(this);
        mGridView.setOnScrollListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_grid_view_demo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        // OnItemSelected callback function didn't get perform
        Log.d(TAG, "Selected item: " + StatesOfUSA.states[position]);
        mSelection.setText(StatesOfUSA.states[position]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        mSelection.setText("");
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        // Log.d(TAG, "Detected scroll state changed");
        switch (scrollState) {
            case SCROLL_STATE_IDLE:
                Log.d(TAG, "Scrolling state: idle");
                break;
            case SCROLL_STATE_TOUCH_SCROLL:
                Log.d(TAG, "Scrolling state: touch scroll");
                break;
            case SCROLL_STATE_FLING:
                Log.d(TAG, "Scrolling state: fling");
                break;
            default:

        }
    }

    /**
     * This will be called on scroll action completed
     */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                         int totalItemCount) {
        Log.d(TAG, "Scrolling state: completed, first item: " +
                StatesOfUSA.states[firstVisibleItem] +
                ", Visible item count: " + visibleItemCount +
                ", Total item count: " + totalItemCount);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        // OnItemSelected callback function didn't get perform
        Log.d(TAG, "Selected item: " + StatesOfUSA.states[position]);
        mSelection.setText(StatesOfUSA.states[position]);
    }
}
