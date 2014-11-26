package ch.tarsier.tarsier.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import ch.tarsier.tarsier.R;
import ch.tarsier.tarsier.domain.model.Peer;
import ch.tarsier.tarsier.util.BitmapFromPath;

/**
 * Created by benjamin on 25/11/14.
 */
public class PeerAdapter extends ArrayAdapter<Peer> {

    private Peer[] tablePeers;
    private Context mContext;
    private int mRowLayoutId;

    public PeerAdapter(Context context, int resource, Peer[] peers) {

        super(context, resource, peers);
        //initiate with fictional peers - to be removed

        tablePeers = peers;
        mContext = context;
        mRowLayoutId = resource;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        PeerHolder peerHolder = null;

        Log.d("PeerAdapter", "mContext is : " + ((Activity) mContext).getLocalClassName());
        if (row == null) {
            //create row
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(mRowLayoutId,parent,false);
            peerHolder = new PeerHolder();

            peerHolder.mProfilePicture = (ImageView) row.findViewById(R.id.nearbyPeerPicture);
            peerHolder.mStatus = (TextView) row.findViewById(R.id.nearbyPeerStatus);
            peerHolder.mUsername = (TextView) row.findViewById(R.id.nearbyPeerName);

            row.setTag(peerHolder);

        } else {
            // recover the information
            peerHolder = (PeerHolder) row.getTag();
        }
        Peer peerToShow = tablePeers[position];
        peerHolder.mUsername.setText(peerToShow.getUserName());
        peerHolder.mStatus.setText(peerToShow.getStatusMessage());
        peerHolder.mProfilePicture.setImageBitmap(
                BitmapFromPath.getBitmapFromPath(mContext, peerToShow.getPicturePath())
        );

        return row;
    }



    private class PeerHolder {
        TextView mUsername;
        TextView mStatus;
        ImageView mProfilePicture;
    }
}