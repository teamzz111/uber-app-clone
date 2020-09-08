package com.unilibre.familiaapp.ui.tools;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.unilibre.familiaapp.R;

public class ToolsFragment extends Fragment {

    private ToolsViewModel toolsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tools, container, false);

        VideoView videoView =(VideoView)root.findViewById(R.id.videoView);
        MediaController mediaController= new MediaController(getActivity());
        mediaController.setAnchorView(videoView);
        Uri uri=Uri.parse("https://vod-progressive.akamaized.net/exp=1599562955~acl=%2Fvimeo-prod-skyfire-std-us%2F01%2F3266%2F16%2F416330724%2F1793467576.mp4~hmac=c21e0b241357458538f1b4d5ca400448440be236cfe3582fd874fecd5af1a695/vimeo-prod-skyfire-std-us/01/3266/16/416330724/1793467576.mp4?filename=Woman+-+38084.mp4");
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.requestFocus();

        videoView.start();
        return root;
    }
}