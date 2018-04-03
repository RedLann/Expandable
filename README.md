# Expandable

[![](https://jitpack.io/v/RedLann/Expandable.svg)](https://jitpack.io/#RedLann/Expandable)

This project was initially forked from [HERE](https://github.com/cachapa/ExpandableLayout "HERE")

### Demo

<img src="https://raw.githubusercontent.com/RedLann/Expandable/master/images/demo.gif" width="408">

### Installation

In project build.gradle

	...
    allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
    
        }
    }

In module build.gradle
    
    implementation 'com.github.RedLann:Expandable:0.5.1'

### How to use (xml)

            <me.vdipalma.expandablepanel.ExpandableHeader
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                app:exclusive_open="true"
                app:expandable_layout_id="@id/associated_expandable_layout"
                app:header_text="Header Title"
                >
    
            </me.vdipalma.expandablepanel.ExpandableHeader>
    
            <me.vdipalma.expandablepanel.ExpandableLayout
                android:id="@+id/associated_expandable_layout"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                app:el_duration="1000"
                app:el_expanded="false"
                app:el_parallax="0.5">
    
                <!-- CONTENT HERE -->
    
            </me.vdipalma.expandablepanel.ExpandableLayout>
