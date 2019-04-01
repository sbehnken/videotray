package com.example.videotray.model;


import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Version;

import java.util.List;

@Root(strict = false)
@NamespaceList({
        @Namespace(reference = "http://search.yahoo.com/mrss/", prefix = "media"),
        @Namespace(reference = "http://www.w3.org/2005/Atom", prefix = "atom")})
public class Rss {

    @Version(revision = 2.0)
    private double version;

    @Element
    private Channel channel;

    public Channel getChannel() {
        return channel;
    }

    @Root(strict = false)
    public static class Channel {

//        @Element
//        private String title;
//
//        public String getTitle() {
//            return title;
//        }

        @ElementList(inline = true, entry = "item")
        private List<Item> itemList;

        public List<Item> getItemList() {
            return itemList;
        }

        @Root(strict = false)
        public static class Item {

            @Element
            private String title;

            public String getTitle() {
                return title;
            }

            @Element(name = "content")
            @Namespace(prefix = "media")
            private MediaContent mediaContent;

            public MediaContent getMediaContent() {
                return mediaContent;
            }

            @Root(strict = false)
            public static class MediaContent {

                @Element(name = "thumbnail")
                @Namespace(prefix = "media")
                private MediaThumbnail mediaThumbnail;

                public MediaThumbnail getMediaThumbnail() {
                    return mediaThumbnail;
                }

                @Attribute
                private double duration;

                public double getDuration() {
                    return duration;
                }

                @Attribute
                private String url;

                public String getUrl() {
                    return url;
                }

                @Root(strict = false)
                public static class MediaThumbnail {
                    @Attribute
                    private String url;

                    public String getUrl() {
                        return url;
                    }
                }
            }
        }
    }
}


