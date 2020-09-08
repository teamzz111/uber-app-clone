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
        Uri uri=Uri.parse("https://vod-progressive.akamaized.net/exp=1599618060~acl=%2Fvimeo-prod-skyfire-std-us%2F01%2F1540%2F16%2F407701799%2F1746637010.mp4~hmac=37c5dabdfb2b203a9ffd2c9388e74d5738c8df408d689de292752fcd8767b5fb/vimeo-prod-skyfire-std-us/01/1540/16/407701799/1746637010.mp4?filename=Cat+-+35733.mp4");
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.requestFocus();

        videoView.start();
        return root;
    }
}