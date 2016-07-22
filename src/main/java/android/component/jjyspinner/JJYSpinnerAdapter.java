package android.component.jjyspinner;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by JJY on 2016/7/12.
 */
public class JJYSpinnerAdapter extends BaseAdapter{

    private Context context;
    private List<String> list;
    private int textColor;
    private int textSize;
    private int gravity;
    public JJYSpinnerAdapter(Context context, List<String> list)
    {
        this.context=context;
        this.list=list;
    }

    public JJYSpinnerAdapter(Context context, List<String> list,int textSize,int textColor,int gravity)
    {
        this.context=context;
        this.list=list;
        this.textColor=textColor;
        this.textSize=textSize;
        this.gravity=gravity;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public String getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null){
            convertView=new TextView(context);
            viewHolder=new ViewHolder();
            viewHolder.tv=(TextView) convertView;
            viewHolder.tv.setTextSize(textSize);
            viewHolder.tv.setTextColor(textColor);
            viewHolder.tv.setGravity(gravity);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder) convertView.getTag();
        }
        viewHolder.tv.setText(getItem(position));
        return convertView;
    }

    class ViewHolder{
        public TextView tv;
    }
}
