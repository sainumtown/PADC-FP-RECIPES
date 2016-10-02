package com.padc.recipes.contents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/***********************************************************************************
 * The MIT License (MIT)

 * Copyright (c) 2015 Scott Cooper

 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:

 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 ***********************************************************************************/


/**
 * A Helper class for providing mock data to the app.
 * In a real world scenario you would either hard code the video ID's in the strings file or
 * retrieve them from a web service.
 */
public class YouTubeContent {

    /**
     * An array of YouTube videos
     */
    public static List<YouTubeVideo> ITEMS = new ArrayList<>();

    /**
     * A map of YouTube videos, by ID.
     */
    public static Map<String, YouTubeVideo> ITEM_MAP = new HashMap<>();

    static {
        addItem(new YouTubeVideo("L8lwlj9D7oQ", "Pork Pone Yae Gyi Curry"));
        addItem(new YouTubeVideo("x-g75uUkKwN-c", "A Chin Hnin"));
        addItem(new YouTubeVideo("3T-YmcusL7Q", "Chicken Potato Curry"));
        addItem(new YouTubeVideo("wdlU4Ct7TWI", "Cucumber Salad"));
        addItem(new YouTubeVideo("ysBBD6pm14U", "Chili Htaung"));
        addItem(new YouTubeVideo("gvwNN6A1LTc", "Tomato Curry"));
        addItem(new YouTubeVideo("NMXpEKaMfDc", "Hta Min Toke "));
        addItem(new YouTubeVideo("I5JbaUjanto", "Hta Min Paung"));
        addItem(new YouTubeVideo("kZl5WpdHtMw", "Si Hta Min"));
        addItem(new YouTubeVideo("8NfFhR_SXyc", "Shan Noodle"));
    }

    private static void addItem(final YouTubeVideo item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A POJO representing a YouTube video
     */
    public static class YouTubeVideo {
        public String id;
        public String title;

        public YouTubeVideo(String id, String content) {
            this.id = id;
            this.title = content;
        }

        @Override
        public String toString() {
            return title;
        }
    }
}