# UE_STF

The **UE_STF** 6G-Library component enables the temporal access to a physical UE through an [STF](https://github.com/DeviceFarmer/stf) (Smartphone Test Farm) Web Interface.
This mobile device has the right SIM to be connected to the physical gNB of the `nokia_radio_uma` component.
The access time for the device is set to 1h by default, but can be modified

> [!NOTE]  
> Currently this component is only available in the site "uma".

## What is STF

<p align="center">
  <a href="https://github.com/DeviceFarmer/stf">
    <img src="https://raw.githubusercontent.com/DeviceFarmer/stf/master/res/common/logo/exports/STF-128.png" width="150" title="UERANSIM">
  </a>
</p>

[**STF**](https://github.com/DeviceFarmer/stf) (or Smartphone Test Farm) is a web application for debugging smartphones, smartwatches and other gadgets remotely from the comfort of your browser.

### Features

* OS support
  - Android
    * Supports versions 2.3.3 (SDK level 10) to 12 (SDK level 32)
    * Supports Wear 5.1 (but not 5.0 due to missing permissions)
    * Supports Fire OS, CyanogenMod, and other heavily Android based distributions
    * `root` is **not** required for any current functionality
* Remote control any device from your browser
  - Real-time screen view
    * Refresh speed can reach 30-40 FPS depending on specs and Android version. See [minicap](https://github.com/devicefarmer/minicap) for more information.
    * Rotation support
  - Supports typing text from your own keyboard
    * Supports meta keys
    * Copy and paste support (although it can be a bit finicky on older devices, you may need to long-press and select paste manually)
    * May sometimes not work well with non-Latin languages unfortunately.
  - Multitouch support on touch screens via [minitouch](https://github.com/devicefarmer/minitouch), two finger pinch/rotate/zoom gesture support on regular screens by pressing `Alt` while dragging
  - Drag & drop installation and launching of `.apk` files
    * Launches main launcher activity if specified in the manifest
  - Reverse port forwarding via [minirev](https://github.com/devicefarmer/minirev)
    * Access your local server directly from the device, even if it's not on the same network
  - Open websites easily in any browser
    * Installed browsers are detected in real time and shown as selectable options
    * Default browser is detected automatically if selected by the user
  - Execute shell commands and see real-time output
  - Display and filter device logs
  - Use `adb connect` to connect to a remote device as if it was plugged in to your computer, regardless of [ADB](http://developer.android.com/tools/help/adb.html) mode and whether you're connected to the same network
    * Run any `adb` command locally, including shell access
    * [Android Studio](http://developer.android.com/tools/studio/index.html) and other IDE support, debug your app while watching the device screen on your browser
    * Supports [Chrome remote debug tools](https://developer.chrome.com/devtools/docs/remote-debugging)
  - File Explorer to access device file system
  - Experimental VNC support (work in progress)
* Monitor your device inventory
  - See which devices are connected, offline/unavailable (indicating a weak USB connection), unauthorized or unplugged
  - See who's using a device
  - Search devices by phone number, IMEI, ICCID, Android version, operator, product name, group name and/or many other attributes with easy but powerful queries
  - Show a bright red screen with identifying information on a device you need to locate physically
  - Track battery level and health
  - Rudimentary Play Store account management
    * List, remove and add new accounts (adding may not work on all devices)
  - Display hardware specs
* Use the Booking & Partitioning systems
  - Overview
    * The partitioning system allow you `[administrator level]` to allocate distinct sets of devices to different projects or organizations (i.e. represented by user sets) for an unlimited period
    * The booking system allows you to reserve a set of devices for a set of users during a limited time (e.g. from 3:00 am to 4:00 am during 5 days)
    * What is common to the booking & partitioning systems is the concept of Group, that is, an association of devices, users and a specification of time
    * Report to [GroupFeature.pdf](doc/GroupFeature.pdf) for detailed documentation on how to use this feature
  - Monitor your group inventory
    * See which groups are active, ready or pending, as well as other group properties: name, identifier, owner, devices, users, class, duration, repetition, starting date, expiration date
    * Search groups by their property values
    * Contact by email the owners of the selected groups 
  - Manage your groups
    * Create a group by specifying its name, devices, users and schedule
    * Get ready your group in order it is scheduled by the system
    * Search groups by their property values
    * Remove your group or a selection of your groups
    * Contact by email the owners of the selected groups `[administrator level]`
* Manage the devices `[administrator level]`
  - Search the devices by their property values
  - Remove a  device or a selection of devices meeting a set of filters: present, booked, annotated, controlled
* Manage the users `[administrator level]`
  - Create a user by providing his name and his email
  - Search the users by their property values
  - Remove a user or a selection of users meeting a set of filters: group owner
  - Contact a user or a selection of users by email
  - Set the default groups quotas applicable to all users
  - Set the groups quotas applicable to a specific user
* Simple REST [API](doc/API.md)