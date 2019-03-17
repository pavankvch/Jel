package com.jelsat.events;

public class FragmentLoadedEvent {
    private boolean isFragmentLoaded;

    public FragmentLoadedEvent(boolean z) {
        this.isFragmentLoaded = z;
    }

    public boolean isFragmentLoaded() {
        return this.isFragmentLoaded;
    }
}
