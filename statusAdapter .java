public  class statusAdapter extends ArrayAdapter<ParseObject>{
    protected Context mContext;
    protected List<ParseObject> mStatus;
    public statusAdapter(Context context, List<ParseObject> status){
        super(context, R.layout.pagehomelayout, status);
        mContext = context;
        mStatus = status;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        ViewHolder holder;
        if(convertView == null)
        {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.pagehomelayout,null);
            holder = new ViewHolder();
            holder.kdiachi = (TextView) convertView.findViewById(R.id.giaphongHP);
            holder.kdientich = (TextView) convertView.findViewById(R.id.dienticHP);
            holder.kgiaphong = (TextView) convertView.findViewById(R.id.diachiHP);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }
        ParseObject statusObject =  mStatus.get(position);
        //title
        String giaphonghp = statusObject.getString("giaphong");
        holder.kgiaphong.setText(giaphonghp + " " + "triệu/tháng");
        // content
        String dientich = statusObject.getString("dientich");
        holder.kdientich.setText(dientich + " " + "(m2)");

        String content = statusObject.getString("diachi");
        holder.kdiachi.setText(content);

        return convertView;
    }
    public  static  class ViewHolder
    {
        TextView kgiaphong;
        TextView kdientich;
        TextView kdiachi;

    }
}
