package encoder;

import java.io.*;
import java.net.*;
import java.util.HashMap;

public class Encoding implements Encoder {

    private String userID;
    private String userKey;
    private String mediaID;
    private ResponseParser rp;
    private HashMap<String, VideoFormat> videoFormats;

    public Encoding(String userID, String userKey) {
        this.userID = userID;
        this.userKey = userKey;
        videoFormats = new HashMap<>();
        fillVideoFormats();
    }

    public String[] getVideoFormats() {
        return (String[]) videoFormats.keySet().toArray();
    }

    @Override
    public String encode(String source, String destination, String format, String notifyURL) {
        String request = "";
        String ret;
        InputStream is;

        request += "<?xml version='1.0'?>";
        request += "<query>";
        request += "    <userid>" + userID + "</userid>";
        request += "    <userkey>" + userKey + "</userkey>";
        request += "    <action>addMedia</action>";
        request += "    <source>" + source + "</source>";
        request += "    <notify>" + notifyURL + "</notify>";
        request += "    <format>";
        request += videoFormats.get(format).getEncodingRequest();
        request += "        <destination>" + destination + "</destination>";
        request += "    </format>";
        request += "</query>";

        is = makeRequest(request);
        if (is == null) {
            return "Error: Encoder service is not responding.";

        } else {
            rp = new ResponseParser(is);

            if ((ret = rp.getValueByElementName("error")) != null) {
                return "Error: " + ret;
            }

            mediaID = rp.getValueByElementName("MediaID");
            return "Encoding Started";
        }
    }

    @Override
    public boolean refreshStatus() {
        InputStream is;
        String request = "";

        request += "<?xml version='1.0'?>" + '\n';
        request += "<query>" + '\n';
        request += "    <userid>" + userID + "</userid>" + '\n';
        request += "    <userkey>" + userKey + "</userkey>" + '\n';
        request += "    <action>getStatus</action>" + '\n';
        request += "    <mediaid>" + mediaID + "</mediaid>" + '\n';
        request += "</query>";

        is = makeRequest(request);
        if (is == null) {
            return false;

        } else {
            rp = new ResponseParser(is);

            return !rp.getValueByElementName("status").equals("Error");
        }
    }

    private InputStream makeRequest(String request) {
        String sRequest, response;
        HttpURLConnection urlConnection;
        BufferedWriter out;
        InputStream is = null;
        String url = "http://manage.encoding.com";
        StringBuffer xml = new StringBuffer();

        xml.append(request);

        URL server = null;

        try {
            server = new URL(url);

        } catch (MalformedURLException mfu) {
            mfu.printStackTrace();
            return null;
        }

        try {
            sRequest = "xml=" + URLEncoder.encode(xml.toString(), "UTF8");

            urlConnection = (HttpURLConnection) server.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.setConnectTimeout(60000);
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            out = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream()));
            out.write(sRequest);
            out.flush();
            out.close();

            urlConnection.connect();
            is = urlConnection.getInputStream();

            response = urlConnection.getResponseCode() + " " + urlConnection.getResponseMessage();
            if (!response.equals("200 OK")) {
                return null;
            }

        } catch (Exception exp) {
            exp.printStackTrace();
        }

        return is;
    }

    @Override
    public String getEncodingStatus() {
        return rp.getValueByElementName("status");
    }

    @Override
    public String getProgress() {
        return rp.getValueByElementName("progress");
    }

    @Override
    public String getRemainingTime() {
        return rp.getValueByElementName("time_left");
    }

    private void fillVideoFormats() {
        VideoFormat vf = new VideoFormat("MP4 H264 3G", "350k", "240p",
                "<output>mp4</output>\n"
                + "<size>0x240</size>\n"
                + "<bitrate>350k</bitrate>\n"
                + "<audio_bitrate>64k</audio_bitrate>\n"
                + "<audio_sample_rate>44100</audio_sample_rate>\n"
                + "<audio_channels_number>2</audio_channels_number>\n"
                + "<framerate>24</framerate>\n"
                + "<keep_aspect_ratio>yes</keep_aspect_ratio>\n"
                + "<video_codec>libx264</video_codec>\n"
                + "<profile>baseline</profile>\n"
                + "<audio_codec>dolby_aac</audio_codec>\n"
                + "<two_pass>no</two_pass>\n"
                + "<turbo>no</turbo>\n"
                + "<twin_turbo>no</twin_turbo>\n"
                + "<cbr>no</cbr>\n"
                + "<deinterlacing>auto</deinterlacing>\n"
                + "<keyframe>120</keyframe>\n"
                + "<audio_volume>100</audio_volume>\n"
                + "<rotate>def</rotate>\n"
                + "<metadata_copy>no</metadata_copy>\n"
                + "<strip_chapters>no</strip_chapters>\n"
                + "<hint>no</hint>");

        videoFormats.put(vf.toString(), vf);
    }
}
