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
        @Element
        private String title;

        public String getTitle() {
            return title;
        }

        @ElementList(inline = true, entry = "item")
        private List<Item> itemList;

        public List<Item> getItemList() {
            return itemList;
        }



        @Root(strict = false)
        public static class Item {
//            private String mediaContent;
//
//            private String getMediaContent() {
//                return mediaContent;
//            }

//            @Root(strict = false)
//            private static class mediaContent {
//                @Attribute
//                private double duration;
//
//                private double getDuration() {
//                    return duration;
//                }
//            }

//            @Root(strict = false)
//            private static class mediaDescription {
//                @Attribute
//                private Image thumbnail;
//
//                private Image getThumbnail() {
//                    return thumbnail;
//                }
//            }
        }
    }
}


