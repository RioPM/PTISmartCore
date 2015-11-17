package com.example.rio.smartcore;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

/**
 * Created by rio on 8/11/15.
 */
class DeviceAdapter extends ArrayAdapter<Device> {

    Context context;
    int id_layout;
    Device[] devices;

    public DeviceAdapter(Context context, int resource, Device[] objects) {
        super(context, resource, objects);
        this.context = context;
        this.id_layout = resource;
        this.devices = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        DeviceHolder holder = null;

        if(row == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(id_layout, parent, false);

            holder = new DeviceHolder();
            holder.deviceIcon = (ImageView)row.findViewById(R.id.imageView);
            holder.deviceSwitch = (Switch)row.findViewById(R.id.device);

            row.setTag(holder);
        }
        else {
            holder = (DeviceHolder)row.getTag();
        }

        Device d = devices[position];
        holder.deviceSwitch.setText(d.name);
        holder.deviceIcon.setImageResource(d.type);
        holder.deviceSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(context, "S'ha canviat l'estat del dispositiu " + position, Toast.LENGTH_SHORT).show();
            }
        });
        return row;
    }

    static class DeviceHolder {
        ImageView deviceIcon;
        Switch deviceSwitch;
    }

}
