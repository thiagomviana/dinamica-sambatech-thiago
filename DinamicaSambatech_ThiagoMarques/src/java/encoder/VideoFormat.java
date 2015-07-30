/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encoder;

/**
 *
 * @author Thiago
 */
public class VideoFormat {
    private String name;
    private String bitrate;
    private String resolution;
    private String encodingRequest;

    public VideoFormat(String name, String bitrate, String resolution, String encodingRequest) {
        this.name = name;
        this.bitrate = bitrate;
        this.resolution = resolution;
        this.encodingRequest = encodingRequest;
    }   

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBitrate() {
        return bitrate;
    }

    public void setBitrate(String bitrate) {
        this.bitrate = bitrate;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getEncodingRequest() {
        return encodingRequest;
    }

    public void setEncodingRequest(String encodingRequest) {
        this.encodingRequest = encodingRequest;
    }        

    @Override
    public String toString() {
        return name + " " + resolution + " " + bitrate;
    }    
}
