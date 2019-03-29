package com.example.videotray.model;

import android.media.Image;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.Version;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.core.ValueRequiredException;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Root(strict = false)
@NamespaceList({
    @Namespace(prefix = "media", reference = "http://search.yahoo.com/mrss/"),
    @Namespace(prefix = "atom", reference = "http://www.w3.org/2005/Atom")})
public class Rss {
    @Attribute(name = "xmlns:media")
    private String xmlns_media;

    @Attribute(name = "xmlns:atom")
    private String xmlns_atom;

    @Version(revision = 1.1)
    private double version;

    public String getXmlns_media() {
        return xmlns_media;
    }

    public String getXmlns_atom() {
        return xmlns_atom;
    }

    @ElementList(name = "channel", inline = true)
    List<Channel> channels = new ArrayList<>();
    private Channel channel;

    public Channel getChannel() {
        return channel;
    }

    static class Channel {
        @Element()
        private String title;

        public String getTitle() {
            return title;
        }

        public class Item {
            @Element(name = "item")
            private String mediaContent;

            private String getMediaContent() {
                return mediaContent;
            }

            public class mediaConent {
                @Attribute
                private double duration;

                private double getDuration() {
                    return duration;
                }
            }

            public class mediaDescription {
                @Attribute
                private Image thumbnail;

                private Image getThumbnail() {
                    return thumbnail;
                }
            }
        }
    }

}


