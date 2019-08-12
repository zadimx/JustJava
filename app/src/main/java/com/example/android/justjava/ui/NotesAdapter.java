package com.example.android.justjava.ui;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Binder;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.justjava.DbActivity;
import com.example.android.justjava.R;
import com.example.android.justjava.db.NotesContract;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Адаптер для заметок
 */
public class NotesAdapter extends CursorRecyclerAdapter<NotesAdapter.ViewHolder> {

    private final OnNoteClickListener onNoteClickListener;
    private final OnNoteClickListenerPhone onNoteClickListenerPhone;
    private final OnNoteClickListenerMap onNoteClickListenerMap;

    private static String[] listDevice = new String[20];

    public static String[] getListDevice() {
        return listDevice;
    }

    public NotesAdapter(Cursor cursor, OnNoteClickListener onNoteClickListener, OnNoteClickListenerPhone onNoteClickListenerPhone, OnNoteClickListenerMap onNoteClickListenerMap) {
        super(cursor);

        this.onNoteClickListener = onNoteClickListener;
        this.onNoteClickListenerPhone = onNoteClickListenerPhone;
        this.onNoteClickListenerMap = onNoteClickListenerMap;
    }




    public void editAxis(ViewHolder viewHolder, String[] a){

//        int axis1ColumnIndex = cursor.getColumnIndexOrThrow(NotesContract.Notes.COLUMN_AXIS1);
//        String axis1 = cursor.getString(axis1ColumnIndex);
        if (a[0] != null && a[0].matches("-?\\d+")) {
            if (Integer.parseInt(a[0]) <= 0 ) {
                viewHolder.axis1.setText(0+" кг");
            }
            else if (Integer.parseInt(a[0]) > 0 && a[0].matches("-?\\d+")) {
//                viewHolder.axis1.setText(Integer.parseInt(axis1)+" кг");
                viewHolder.axis1.setText(a[0]+" кг");
            }
        }


//        int axis2ColumnIndex = cursor.getColumnIndexOrThrow(NotesContract.Notes.COLUMN_AXIS2);
//        String axis2 = cursor.getString(axis2ColumnIndex);
        if (a[1] != null && a[1].matches("-?\\d+")) {
            if (Integer.parseInt(a[1]) <= 0) {
                viewHolder.axis2.setText(0+" кг");
            }
            else if (Integer.parseInt(a[1]) > 0 && a[1].matches("-?\\d+")) {
//                viewHolder.axis2.setText(Integer.parseInt(axis2)+" кг");
                viewHolder.axis2.setText(a[1]+" кг");
            }
        }


        if (a[0] != null && a[1] != null && a[0].matches("-?\\d+") && a[1].matches("-?\\d+")) {
            if (Integer.parseInt(a[0]) <= 0 && Integer.parseInt(a[1]) <= 0) {
                viewHolder.axisMidNext.setText(0+" кг");
                Log.d("ааа", "33R1");
            }
            else if (Integer.parseInt(a[0]) <= 0) {
                viewHolder.axisMidNext.setText((Integer.parseInt(a[0])+" кг"));
                Log.d("ааа", "33R2");
            }
            else if (Integer.parseInt(a[0]) <= 0) {
                viewHolder.axisMidNext.setText((Integer.parseInt(a[1])+" кг"));
                Log.d("ааа", "33R3");
            }
            else {viewHolder.axisMidNext.setText((Integer.parseInt(a[0])+Integer.parseInt(a[1]))+" кг");
            Log.d("ааа", "33R4  "+(Integer.parseInt(a[0]))+" "+Integer.parseInt(a[1]));}
        }


        // КОСТЫЛЬ РАЗКОМИТИТЬ!!!!!!!!

//        int axis3ColumnIndex = cursor.getColumnIndexOrThrow(NotesContract.Notes.COLUMN_AXIS3);
//        String axis3 = cursor.getString(axis3ColumnIndex);
//        viewHolder.axis3.setText(axis3+" т");


//        int axis4ColumnIndex = cursor.getColumnIndexOrThrow(NotesContract.Notes.COLUMN_AXIS3);
//        String axis4 = cursor.getString(axis4ColumnIndex);
        if (a[3] != null && a[3].matches("-?\\d+")) {
            if (Integer.parseInt(a[3]) <= 0) {
                viewHolder.axis4.setText(0+" кг");
            }
            else if (Integer.parseInt(a[3]) > 0 ) {
//                viewHolder.axis4.setText(Integer.parseInt(axis4)+" кг");
                viewHolder.axis4.setText(a[3]+" кг");
            }
        }



        // КОСТЫЛЬ УДАЛИТЬ!!!!!!!!!!

//        int axis3ColumnIndex = cursor.getColumnIndexOrThrow(NotesContract.Notes.COLUMN_AXIS3);
//        String axis3 = cursor.getString(axis3ColumnIndex);

        if (a[2] != null && a[2].matches("-?\\d+")) {
//            a[2] = Integer.parseInt(a[2]) + (int)Math.round(Math.random()*100)+"";
            if (Integer.parseInt(a[2]) <= 0 ) {
                viewHolder.axis3.setText(0+" кг");
            }
            else if (Integer.parseInt(a[2]) > 0 && a[2].matches("-?\\d+")) {
//                viewHolder.axis3.setText(Integer.parseInt(axis3)+" кг");
                viewHolder.axis3.setText(a[2]+" кг");
            }
        }


        if (a[2] != null && a[3] != null && a[2].matches("-?\\d+") && a[3].matches("-?\\d+")) {
            if (Integer.parseInt(a[2]) <= 0 && Integer.parseInt(a[3]) <= 0) {
                viewHolder.axisMidBack.setText(0 + " кг");
            } else if (Integer.parseInt(a[2]) <= 0) {
                viewHolder.axisMidBack.setText((Integer.parseInt(a[2]) + " кг"));
            } else if (Integer.parseInt(a[3]) <= 0) {
                viewHolder.axisMidBack.setText((Integer.parseInt(a[3]) + " кг"));
            } else
                viewHolder.axisMidBack.setText((Integer.parseInt(a[2]) + Integer.parseInt(a[3])) + " кг");
        }
    }





    // В этом методе мы привязываем данные к ячейке
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Cursor cursor) {
        int idColumnIndex = cursor.getColumnIndexOrThrow(NotesContract.Notes._ID);
        long id = cursor.getLong(idColumnIndex);

        if (id == 1) {
                //ID ячейки view!!!!!!!!!
                listDevice[(int)id-1] = DbActivity.getNumberDevice()[(int)id-1];
                viewHolder.itemView.setTag(id);
                Log.d("NumberDev","NumberDev "+DbActivity.getNumberDevice()[(int)id-1]);

            editAxis(viewHolder, DbActivity.getT1());
        }
        else if(id == 2){
            //ID ячейки view!!!!!!!!!
                listDevice[(int)id-1] = DbActivity.getNumberDevice()[(int)id-1];
            viewHolder.itemView.setTag(id);
            Log.d("NumberDev","NumberDev "+DbActivity.getNumberDevice()[(int)id-1]);
            editAxis(viewHolder, DbActivity.getT2());
        }
        else if(id == 3){
            //ID ячейки view!!!!!!!!!
                listDevice[(int)id-1] = DbActivity.getNumberDevice()[(int)id-1];
            viewHolder.itemView.setTag(id);
            Log.d("NumberDev","NumberDev "+DbActivity.getNumberDevice()[(int)id-1]);
            editAxis(viewHolder, DbActivity.getT3());
        }
        else if(id == 4){
            //ID ячейки view!!!!!!!!!
                listDevice[(int)id-1] = DbActivity.getNumberDevice()[(int)id-1];
            viewHolder.itemView.setTag(id);
            Log.d("NumberDev","NumberDev "+DbActivity.getNumberDevice()[(int)id-1]);
            editAxis(viewHolder, DbActivity.getT4());
        }
        else if(id == 5){
            //ID ячейки view!!!!!!!!!
                listDevice[(int)id-1] = DbActivity.getNumberDevice()[(int)id-1];
            viewHolder.itemView.setTag(id);
            Log.d("NumberDev","NumberDev "+DbActivity.getNumberDevice()[(int)id-1]);
            editAxis(viewHolder, DbActivity.getT5());
        }
        else if(id == 6){
            //ID ячейки view!!!!!!!!!
                listDevice[(int)id-1] = DbActivity.getNumberDevice()[(int)id-1];
            viewHolder.itemView.setTag(id);
            Log.d("NumberDev","NumberDev "+DbActivity.getNumberDevice()[(int)id-1]);
            editAxis(viewHolder, DbActivity.getT6());
        }
        else if(id == 7){
            //ID ячейки view!!!!!!!!!

                listDevice[(int)id-1] = DbActivity.getNumberDevice()[(int)id-1];

            viewHolder.itemView.setTag(id);
            Log.d("NumberDev","NumberDev "+DbActivity.getNumberDevice()[(int)id-1]);
            editAxis(viewHolder, DbActivity.getT7());
        }
        else if(id == 8){
            //ID ячейки view!!!!!!!!!
                listDevice[(int)id-1] = DbActivity.getNumberDevice()[(int)id-1];
            viewHolder.itemView.setTag(id);
            editAxis(viewHolder, DbActivity.getT8());
        }
        else if(id == 9){
            //ID ячейки view!!!!!!!!!
                listDevice[(int)id-1] = DbActivity.getNumberDevice()[(int)id-1];
            viewHolder.itemView.setTag(id);
            Log.d("NumberDev","NumberDev "+DbActivity.getNumberDevice()[(int)id-1]);
            editAxis(viewHolder, DbActivity.getT9());
        }
        else if(id == 10){
            //ID ячейки view!!!!!!!!!
                listDevice[(int)id-1] = DbActivity.getNumberDevice()[(int)id-1];
            viewHolder.itemView.setTag(id);
            Log.d("NumberDev","NumberDev "+DbActivity.getNumberDevice()[(int)id-1]);
            editAxis(viewHolder, DbActivity.getT10());
        }
        else if(id == 11){
            //ID ячейки view!!!!!!!!!
                listDevice[(int)id-1] = DbActivity.getNumberDevice()[(int)id-1];

            viewHolder.itemView.setTag(id);
            Log.d("NumberDev","NumberDev "+DbActivity.getNumberDevice()[(int)id-1]);
            editAxis(viewHolder, DbActivity.getT11());
        }
        else if(id == 12){
            //ID ячейки view!!!!!!!!!
                listDevice[(int)id-1] = DbActivity.getNumberDevice()[(int)id-1];
            viewHolder.itemView.setTag(id);
            Log.d("NumberDev","NumberDev "+DbActivity.getNumberDevice()[(int)id-1]);
            editAxis(viewHolder, DbActivity.getT12());
        }
        else if(id == 13){
            //ID ячейки view!!!!!!!!!
            if (listDevice[(int)id-1]==null) {
                listDevice[(int)id-1] = DbActivity.getNumberDevice()[(int)id-1];
            }
            else listDevice[(int)id-1] = (DbActivity.getNumberDevice()[(int)id-1]);
            viewHolder.itemView.setTag(id);
            Log.d("NumberDev","NumberDev "+DbActivity.getNumberDevice()[(int)id-1]);
            editAxis(viewHolder, DbActivity.getT13());
        }
        else if(id == 14){
            //ID ячейки view!!!!!!!!!
            if (listDevice[(int)id-1]==null) {
                listDevice[(int)id-1] = DbActivity.getNumberDevice()[(int)id-1];
            }
            else listDevice[(int)id-1] = (DbActivity.getNumberDevice()[(int)id-1]);
            viewHolder.itemView.setTag(id);
            Log.d("NumberDev","NumberDev "+DbActivity.getNumberDevice()[(int)id-1]);
            editAxis(viewHolder, DbActivity.getT14());
        }
        else if(id == 15){
            //ID ячейки view!!!!!!!!!
            if (listDevice[(int)id-1]==null) {
                listDevice[(int)id-1] = DbActivity.getNumberDevice()[(int)id-1];
            }
            else listDevice[(int)id-1] = (DbActivity.getNumberDevice()[(int)id-1]);
            viewHolder.itemView.setTag(id);
            Log.d("NumberDev","NumberDev "+DbActivity.getNumberDevice()[(int)id-1]);
            editAxis(viewHolder, DbActivity.getT15());
        }
        else if(id == 16){
            //ID ячейки view!!!!!!!!!
            if (listDevice[(int)id-1]==null) {
                listDevice[(int)id-1] = DbActivity.getNumberDevice()[(int)id-1];
            }
            else listDevice[(int)id-1] = (DbActivity.getNumberDevice()[(int)id-1]);
            viewHolder.itemView.setTag(id);
            Log.d("NumberDev","NumberDev "+DbActivity.getNumberDevice()[(int)id-1]);
            editAxis(viewHolder, DbActivity.getT16());
        }
        else if(id == 17){
            //ID ячейки view!!!!!!!!!
            if (listDevice[(int)id-1]==null) {
                listDevice[(int)id-1] = DbActivity.getNumberDevice()[(int)id-1];
            }
            else listDevice[(int)id-1] = (DbActivity.getNumberDevice()[(int)id-1]);
            viewHolder.itemView.setTag(id);
            Log.d("NumberDev","NumberDev "+DbActivity.getNumberDevice()[(int)id-1]);
            editAxis(viewHolder, DbActivity.getT17());
        }
        else if(id == 18){
            //ID ячейки view!!!!!!!!!
            if (listDevice[(int)id-1]==null) {
                listDevice[(int)id-1] = DbActivity.getNumberDevice()[(int)id-1];
            }
            else listDevice[(int)id-1] = (DbActivity.getNumberDevice()[(int)id-1]);
            viewHolder.itemView.setTag(id);
            Log.d("NumberDev","NumberDev "+DbActivity.getNumberDevice()[(int)id-1]);
            editAxis(viewHolder, DbActivity.getT18());
        }
        else if(id == 19){
            //ID ячейки view!!!!!!!!!
            if (listDevice[(int)id-1]==null) {
                listDevice[(int)id-1] = DbActivity.getNumberDevice()[(int)id-1];
            }
            else listDevice[(int)id-1] = (DbActivity.getNumberDevice()[(int)id-1]);
            viewHolder.itemView.setTag(id);
            Log.d("NumberDev","NumberDev "+DbActivity.getNumberDevice()[(int)id-1]);
            editAxis(viewHolder, DbActivity.getT19());
        }
        else if(id == 20){
            //ID ячейки view!!!!!!!!!
            if (listDevice[(int)id-1]==null) {
                listDevice[(int)id-1] = DbActivity.getNumberDevice()[(int)id-1];
            }
            else listDevice[(int)id-1] = (DbActivity.getNumberDevice()[(int)id-1]);
            viewHolder.itemView.setTag(id);
            Log.d("NumberDev","NumberDev "+DbActivity.getNumberDevice()[(int)id-1]);
            editAxis(viewHolder, DbActivity.getT20());
        }




        viewHolder.button_map.setTag(id*1000);


        int titleColumnIndex = cursor.getColumnIndexOrThrow(NotesContract.Notes.COLUMN_TITLE);
        String nameDriver = cursor.getString(titleColumnIndex);
        viewHolder.name_driver.setText("("+nameDriver+")");

        int noteColumnIndex = cursor.getColumnIndexOrThrow(NotesContract.Notes.COLUMN_NOTE);
        String nameDeviceList = cursor.getString(noteColumnIndex);
        viewHolder.number_truck.setText(nameDeviceList);



        int numberPhoneColumnIndex = cursor.getColumnIndexOrThrow(NotesContract.Notes.COLUMN_NUMBER_PHONE);
        String namberPhone = cursor.getString(numberPhoneColumnIndex);
        namberPhone = namberPhone.replaceAll("\\D+","");
        viewHolder.icon_phone.setTag(Long.parseLong(namberPhone));
//        viewHolder.axis4.setText(axis4);


        int dateColumnIndex = cursor.getColumnIndexOrThrow(NotesContract.Notes.COLUMN_UPDATED_TS);
        long updatedTs = cursor.getLong(dateColumnIndex);
        Date date = new Date(updatedTs);

//        viewHolder.dateTv.setText(viewHolder.SDF.format(date));
    }

    // В этом методе мы создаем новую ячейку
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.view_item_note, parent, false);
        return new ViewHolder(view);
    }


    // В этом методе мы возвращаем количество элементов списка
//    @Override
//    public int getItemCount() {
//        return 4;
//    }

    /**
     * View holder
     * Хранит информацию о ячейке
     */
    class ViewHolder extends RecyclerView.ViewHolder {

        final SimpleDateFormat SDF = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault());

        private final TextView number_truck;
        private final TextView name_driver;
        private final TextView axis1;
        private final TextView axisMidNext;
        private final TextView axis2;
        private final TextView axis3;
        private final TextView axisMidBack;
        private final TextView axis4;
        private final ImageView icon_phone;
        private final ImageView button_map;
//        private final TextView dateTv;


        public ViewHolder(View itemView) {
            super(itemView);

            this.number_truck = itemView.findViewById(R.id.number_truck);
            this.name_driver = itemView.findViewById(R.id.name_driver);
            this.axis1 = itemView.findViewById(R.id.axis1);
            this.axis2 = itemView.findViewById(R.id.axis2);
            this.axis3 = itemView.findViewById(R.id.axis3);
            this.axis4 = itemView.findViewById(R.id.axis4);
            this.axisMidNext = itemView.findViewById(R.id.axis_mid_next);
            this.axisMidBack = itemView.findViewById(R.id.axis_mid_back);
            this.icon_phone = itemView.findViewById(R.id.icon_phone);
            this.button_map = itemView.findViewById(R.id.button_map);
//            this.dateTv = itemView.findViewById(R.id.date_tv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        long noteId = (Long) v.getTag();
                        onNoteClickListener.onNoteClick(noteId);
                }
            });
            icon_phone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    long noteId = (Long) v.getTag();

                    onNoteClickListenerPhone.onNoteClickPhone(noteId);
                }
            });
            button_map.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    long noteId = (Long) v.getTag();
                    onNoteClickListenerMap.onNoteClickMap(noteId);
                }
            });
        }
    }

    /**
     * Слушатель для обработки кликов
     */
    public interface OnNoteClickListener {
        void onNoteClick(long noteId);
    }

    /**
     * Слушатель для обработки кликов звонка
     */
    public interface OnNoteClickListenerPhone {
        void onNoteClickPhone(long noteId);
    }

    /**
     * Слушатель для обработки кликов звонка
     */
    public interface OnNoteClickListenerMap {
        void onNoteClickMap(long noteId);
    }
}
