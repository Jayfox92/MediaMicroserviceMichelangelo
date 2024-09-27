package com.michelangelo.mediamicroservice.VO;

public class StreamHistory {
    private long id;
    private int mediaId;
    private int streamHistoryCount;

    public StreamHistory() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getMediaId() {
        return mediaId;
    }

    public void setMediaId(int mediaId) {
        this.mediaId = mediaId;
    }

    public int getStreamHistoryCount() {
        return streamHistoryCount;
    }

    public void setStreamHistoryCount(int streamHistoryCount) {
        this.streamHistoryCount = streamHistoryCount;
    }
}
