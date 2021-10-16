SCRIPT_START
{
SCRIPT_NAME VPHONE
NOP

LVAR_INT hour min scplayer phone_model pos_x pos_y selec b_active num_items theme_r theme_g theme_b dummy
LVAR_FLOAT selec_mult

CONST_FLOAT SCREEN_WIDTH 92.0

GET_PLAYER_CHAR 0 scplayer
REQUEST_ANIMATION phone

theme_r = 109
theme_g = 235
theme_b = 95
dummy = 0

init:
WAIT 0
IF IS_BUTTON_PRESSED 0 DPADUP
    SET_PLAYER_CONTROL 0 0
    LOAD_TEXTURE_DICTIONARY VPHONE
    GOSUB load_cellphone
    GOTO init_phone
ENDIF
GOTO init

load_cellphone:
REQUEST_MODEL CELLPHONE
WHILE NOT HAS_MODEL_LOADED CELLPHONE
    WAIT 0
ENDWHILE
MARK_MODEL_AS_NO_LONGER_NEEDED CELLPHONE
CREATE_OBJECT_NO_OFFSET CELLPHONE 0.0 0.0 0.0 phone_model
TASK_PICK_UP_OBJECT scplayer phone_model 0.03 0.0 0.0 6 16 "phone_grab" "PHONE" -1
IF IS_CHAR_IN_ANY_CAR scplayer
    TASK_PLAY_ANIM scplayer "phone_grab" "PHONE" 4.0 1 0 0 0 -1
ENDIF
RETURN

init_phone:
WHILE IS_BUTTON_PRESSED 0 CIRCLE
OR IS_BUTTON_PRESSED 0 CROSS
    GOSUB draw_home
ENDWHILE
GOSUB draw_home
IF b_active = FALSE
    IF IS_BUTTON_PRESSED 0 DPADLEFT
       selec -= 1
       b_active = TRUE
    ENDIF
    IF IS_BUTTON_PRESSED 0 DPADRIGHT
        selec += 1
        b_active = TRUE
    ENDIF
    IF IS_BUTTON_PRESSED 0 DPADUP
       selec -= 3
       b_active = TRUE
    ENDIF
    IF IS_BUTTON_PRESSED 0 DPADDOWN
        selec += 3
        b_active = TRUE
    ENDIF
ELSE
    IF NOT IS_BUTTON_PRESSED 0 DPADLEFT
    AND NOT IS_BUTTON_PRESSED 0 DPADRIGHT
    AND NOT IS_BUTTON_PRESSED 0 DPADUP
    AND NOT IS_BUTTON_PRESSED 0 DPADDOWN
        b_active = FALSE
    ENDIF
ENDIF
IF selec < 0
    selec = 7
ENDIF
IF selec > 7
    selec = 0
ENDIF

IF IS_BUTTON_PRESSED 0 CROSS
    SWITCH selec
        CASE 0
            BREAK
        CASE 1
            BREAK
        CASE 2
            BREAK
        CASE 3
            BREAK
        CASE 4
            BREAK
        CASE 5
            selec = 0
            GOTO create_settings
            BREAK
        CASE 6
            BREAK
        CASE 7
            BREAK
    ENDSWITCH
ENDIF

IF IS_BUTTON_PRESSED 0 CIRCLE
    WAIT 0
    REMOVE_TEXTURE_DICTIONARY
    DELETE_OBJECT phone_model
    CLEAR_CHAR_TASKS scplayer
    SET_PLAYER_CONTROL 0 1
    GOTO init
ENDIF
GOTO init_phone

draw_home:
USE_TEXT_COMMANDS 1
GOSUB rainbow_theme
LOAD_SPRITE 4 wallpaper
DRAW_SPRITE 4 560.0 329.0 SCREEN_WIDTH 154.0 theme_r theme_g theme_b 255
LOAD_SPRITE 3 home
DRAW_SPRITE 3 560.0 277.0 SCREEN_WIDTH 50.0 255 255 255 255
GOSUB draw_phone_frame
SET_TEXT_EDGE 0 0 0 0 0
SET_TEXT_SCALE 0.25 1.3
DISPLAY_TEXT 546.0 271.5 PZERO
SET_TEXT_EDGE 0 0 0 0 0
SET_TEXT_SCALE 0.25 1.3
DISPLAY_TEXT 565.0 271.5 PZERO
GET_TIME_OF_DAY hour min
SET_TEXT_EDGE 0 0 0 0 0
SET_TEXT_SCALE 0.35 1.9
DISPLAY_TEXT_WITH_2_NUMBERS 549.0 253.0 PTIME hour min 
LOAD_SPRITE 5 selected
SWITCH selec
    CASE 0
        GOSUB set_app_title
        DISPLAY_TEXT 545.0 283.0 PEMAIL
        DRAW_SPRITE 5 533.0 318.0 27.0 34.2 theme_r theme_g theme_b 255
        BREAK
    CASE 1
        GOSUB set_app_title
        DISPLAY_TEXT 535.0 283.0 PMESSAG
        DRAW_SPRITE 5 560.0 318.0 27.0 34.2 theme_r theme_g theme_b 255
        BREAK
    CASE 2
        GOSUB set_app_title
        DISPLAY_TEXT 540.0 283.0 PCHECKL
        DRAW_SPRITE 5 587.0 318.0 27.0 34.2 theme_r theme_g theme_b 255
        BREAK
    CASE 3
        GOSUB set_app_title
        DISPLAY_TEXT 534.0 283.0 PQKSAVE
        DRAW_SPRITE 5 533.0 350.0 27.0 34.2 theme_r theme_g theme_b 255
        BREAK
    CASE 4
        GOSUB set_app_title
        DISPLAY_TEXT 539.0 283.0 PCONTAC
        DRAW_SPRITE 5 560.0 350.0 27.0 34.2 theme_r theme_g theme_b 255
        BREAK
    CASE 5
        GOSUB set_app_title
        DISPLAY_TEXT 539.0 283.0 PSETTIN
        DRAW_SPRITE 5 587.0 350.0 27.0 34.2 theme_r theme_g theme_b 255
        BREAK
    CASE 6
        GOSUB set_app_title
        DISPLAY_TEXT 536.0 283.0 PSNPMTC
        DRAW_SPRITE 5 533.0 382.0 27.0 34.2 theme_r theme_g theme_b 255
        BREAK
    CASE 7
        GOSUB set_app_title
        DISPLAY_TEXT 540.0 283.0 PBROWSE
        DRAW_SPRITE 5 560.0 382.0 27.0 34.2 theme_r theme_g theme_b 255
        BREAK
ENDSWITCH
LOAD_SPRITE 6 email
DRAW_SPRITE 6 533.0 318.0 27.0 34.2 255 255 255 255
LOAD_SPRITE 7 messages
DRAW_SPRITE 7 560.0 318.0 27.0 34.2 255 255 255 255
LOAD_SPRITE 8 checklist
DRAW_SPRITE 8 587.0 318.0 27.0 34.2 255 255 255 255
LOAD_SPRITE 9 quicksave
DRAW_SPRITE 9 533.0 350.0 27.0 34.2 255 255 255 255
LOAD_SPRITE 10 contacts
DRAW_SPRITE 10 560.0 350.0 27.0 34.2 255 255 255 255
LOAD_SPRITE 11 settings
DRAW_SPRITE 11 587.0 350.0 27.0 34.2 255 255 255 255
LOAD_SPRITE 12 snapmatic
DRAW_SPRITE 12 533.0 382.0 27.0 34.2 255 255 255 255
LOAD_SPRITE 13 browser
DRAW_SPRITE 13 560.0 382.0 27.0 34.2 255 255 255 255
USE_TEXT_COMMANDS 0
WAIT 0
RETURN

create_settings:
WHILE IS_BUTTON_PRESSED 0 CIRCLE
OR IS_BUTTON_PRESSED 0 CROSS
    GOSUB draw_settings
ENDWHILE
GOSUB draw_settings

IF IS_BUTTON_PRESSED 0 CROSS
    SWITCH selec
        CASE 0
            BREAK
        CASE 1
            BREAK
        CASE 2
            BREAK
        CASE 3
            selec = 0
            GOTO create_theme_sets
            BREAK
        CASE 4
            BREAK
    ENDSWITCH
ENDIF

IF IS_BUTTON_PRESSED 0 CIRCLE
    GOTO init_phone
ENDIF
GOTO create_settings

draw_settings:
USE_TEXT_COMMANDS 1
GOSUB rainbow_theme
GOSUB create_menu_base
GOSUB set_app_title
DISPLAY_TEXT 540.0 262.0 PSETTIN
num_items = 4
GOSUB create_menu_selection
GOSUB set_settings_item
LOAD_SPRITE 4 theme_icon
IF selec = 0
    SET_TEXT_COLOUR 255 255 255 255
    DRAW_SPRITE 4 522.0 291.2 15.0 19.0 255 255 255 255
ELSE
    DRAW_SPRITE 4 522.0 291.2 15.0 19.0 theme_r theme_g theme_b 255
ENDIF
DISPLAY_TEXT 532.0 283.0 PWALLPA
GOSUB set_settings_item
LOAD_SPRITE 5 profile_icon
IF selec = 1
    SET_TEXT_COLOUR 255 255 255 255
    DRAW_SPRITE 5 522.0 313.2 15.0 19.0 255 255 255 255
ELSE
    DRAW_SPRITE 5 522.0 313.2 15.0 19.0 theme_r theme_g theme_b 255
ENDIF
DISPLAY_TEXT 532.0 305.0 PPROFIL
GOSUB set_settings_item
LOAD_SPRITE 6 ringtone_icon
IF selec = 2
    SET_TEXT_COLOUR 255 255 255 255
    DRAW_SPRITE 6 522.0 335.2 15.0 19.0 255 255 255 255
ELSE
    DRAW_SPRITE 6 522.0 335.2 15.0 19.0 theme_r theme_g theme_b 255
ENDIF
DISPLAY_TEXT 532.0 327.0 PRINGTO
GOSUB set_settings_item
LOAD_SPRITE 4 theme_icon
IF selec = 3
    SET_TEXT_COLOUR 255 255 255 255
    DRAW_SPRITE 4 522.0 357.2 15.0 19.0 255 255 255 255
ELSE
    DRAW_SPRITE 4 522.0 357.2 15.0 19.0 theme_r theme_g theme_b 255
ENDIF
DISPLAY_TEXT 532.0 349.0 PTHEME
GOSUB set_settings_item
LOAD_SPRITE 7 vibrate_icon
IF selec = 4
    SET_TEXT_COLOUR 255 255 255 255
    DRAW_SPRITE 7 522.0 379.2 15.0 19.0 255 255 255 255
ELSE
    DRAW_SPRITE 7 522.0 379.2 15.0 19.0 theme_r theme_g theme_b 255
ENDIF
DISPLAY_TEXT 532.0 371.0 PVIBRAT
GOSUB draw_phone_frame
USE_TEXT_COMMANDS 0
WAIT 0
RETURN

create_theme_sets:
WHILE IS_BUTTON_PRESSED 0 CIRCLE
OR IS_BUTTON_PRESSED 0 CROSS
    GOSUB draw_theme_sets
ENDWHILE
GOSUB draw_theme_sets

IF IS_BUTTON_PRESSED 0 CROSS
    SWITCH selec
        CASE 0
            theme_r = 80
            theme_g = 173
            theme_b = 239
            BREAK
        CASE 1
            theme_r = 109
            theme_g = 235
            theme_b = 95
            BREAK
        CASE 2
            theme_r = 89
            theme_g = 89
            theme_b = 88
            BREAK
        CASE 3
            theme_r = 206
            theme_g = 111
            theme_b = 29
            BREAK
        CASE 4
            theme_r = 255
            theme_g = 108
            theme_b = 200
            BREAK
        CASE 5
            BREAK
    ENDSWITCH
ENDIF
IF IS_BUTTON_PRESSED 0 DPADDOWN
AND selec = 5
    selec = 1
    GOTO create_theme_sets2
ENDIF
IF IS_BUTTON_PRESSED 0 CIRCLE
    GOTO create_settings
ENDIF
GOTO create_theme_sets

draw_theme_sets:
USE_TEXT_COMMANDS 1
GOSUB rainbow_theme
LOAD_SPRITE 4 theme_icon
LOAD_SPRITE 5 ok_icon
GOSUB create_menu_base
GOSUB set_app_title
DISPLAY_TEXT 540.0 262.0 PSETTIN
num_items = 5
GOSUB create_menu_selection
GOSUB set_settings_item
IF selec = 0
    SET_TEXT_COLOUR 255 255 255 255
    DRAW_SPRITE 4 522.0 291.2 15.0 19.0 255 255 255 255
ELSE
    DRAW_SPRITE 4 522.0 291.2 15.0 19.0 theme_r theme_g theme_b 255
ENDIF
DISPLAY_TEXT 532.0 283.0 PBLUE
GOSUB set_settings_item
IF selec = 1
    SET_TEXT_COLOUR 255 255 255 255
    DRAW_SPRITE 4 522.0 313.2 15.0 19.0 255 255 255 255
ELSE
    DRAW_SPRITE 4 522.0 313.2 15.0 19.0 theme_r theme_g theme_b 255
ENDIF
DISPLAY_TEXT 532.0 305.0 PGREEN
GOSUB set_settings_item
IF selec = 2
    SET_TEXT_COLOUR 255 255 255 255
    DRAW_SPRITE 4 522.0 335.2 15.0 19.0 255 255 255 255
ELSE
    DRAW_SPRITE 4 522.0 335.2 15.0 19.0 theme_r theme_g theme_b 255
ENDIF
DISPLAY_TEXT 532.0 327.0 PGRAY
GOSUB set_settings_item
IF selec = 3
    SET_TEXT_COLOUR 255 255 255 255
    DRAW_SPRITE 4 522.0 357.2 15.0 19.0 255 255 255 255
ELSE
    DRAW_SPRITE 4 522.0 357.2 15.0 19.0 theme_r theme_g theme_b 255
ENDIF
DISPLAY_TEXT 532.0 349.0 PORANGE
GOSUB set_settings_item
IF selec = 4
    SET_TEXT_COLOUR 255 255 255 255
    DRAW_SPRITE 4 522.0 379.2 15.0 19.0 255 255 255 255
ELSE
    DRAW_SPRITE 4 522.0 379.2 15.0 19.0 theme_r theme_g theme_b 255
ENDIF
DISPLAY_TEXT 532.0 371.0 PPINK
GOSUB draw_phone_frame
USE_TEXT_COMMANDS 0
WAIT 0
RETURN

create_theme_sets2:
WHILE IS_BUTTON_PRESSED 0 CIRCLE
OR IS_BUTTON_PRESSED 0 CROSS
    GOSUB draw_theme_sets2
ENDWHILE
GOSUB draw_theme_sets2

IF IS_BUTTON_PRESSED 0 CROSS
    SWITCH selec
        CASE 0
            BREAK
        CASE 1
            dummy = 0
            theme_r = 122
            theme_g = 65
            theme_b = 148
            BREAK
        CASE 2
            dummy = 0
            theme_r = 216
            theme_g = 33
            theme_b = 32
            BREAK
        CASE 3
            dummy = 0
            theme_r = 255
            theme_g = 234
            theme_b = 0
            BREAK
        CASE 4
            dummy = 1
            BREAK
    ENDSWITCH
ENDIF
IF IS_BUTTON_PRESSED 0 DPADUP
AND selec = 0
    selec = 4
    GOTO create_theme_sets
ENDIF
IF IS_BUTTON_PRESSED 0 CIRCLE
    GOTO create_settings
ENDIF
GOTO create_theme_sets2

draw_theme_sets2:
USE_TEXT_COMMANDS 1
GOSUB rainbow_theme
LOAD_SPRITE 4 theme_icon
LOAD_SPRITE 5 ok_icon
GOSUB create_menu_base
GOSUB set_app_title
DISPLAY_TEXT 540.0 262.0 PSETTIN
num_items = 4
GOSUB create_menu_selection
GOSUB set_settings_item
IF selec = 1
    SET_TEXT_COLOUR 255 255 255 255
    DRAW_SPRITE 4 522.0 313.2 15.0 19.0 255 255 255 255
ELSE
    DRAW_SPRITE 4 522.0 313.2 15.0 19.0 theme_r theme_g theme_b 255
ENDIF
DISPLAY_TEXT 532.0 305.0 PPURPLE
GOSUB set_settings_item
IF selec = 2
    SET_TEXT_COLOUR 255 255 255 255
    DRAW_SPRITE 4 522.0 335.2 15.0 19.0 255 255 255 255
ELSE
    DRAW_SPRITE 4 522.0 335.2 15.0 19.0 theme_r theme_g theme_b 255
ENDIF
DISPLAY_TEXT 532.0 327.0 PRED
GOSUB set_settings_item
IF selec = 3
    SET_TEXT_COLOUR 255 255 255 255
    DRAW_SPRITE 4 522.0 357.2 15.0 19.0 255 255 255 255
ELSE
    DRAW_SPRITE 4 522.0 357.2 15.0 19.0 theme_r theme_g theme_b 255
ENDIF
DISPLAY_TEXT 532.0 349.0 PYELLOW
GOSUB set_settings_item
IF selec = 4
    SET_TEXT_COLOUR 255 255 255 255
    DRAW_SPRITE 4 522.0 379.2 15.0 19.0 255 255 255 255
ELSE
    DRAW_SPRITE 4 522.0 379.2 15.0 19.0 theme_r theme_g theme_b 255
ENDIF
DISPLAY_TEXT 532.0 371.0 PRAINBO
GOSUB draw_phone_frame
USE_TEXT_COMMANDS 0
WAIT 0
RETURN

create_menu_base:
LOAD_SPRITE 3 header_bar
DRAW_RECT 560.0 334.0 SCREEN_WIDTH 144.0 255 255 255 255
DRAW_RECT 560.0 257.0 SCREEN_WIDTH 10.0 0 0 0 255
DRAW_SPRITE 3 560.0 271.2 SCREEN_WIDTH 18.0 theme_r theme_g theme_b 255
RETURN

create_menu_selection:
IF b_active = FALSE
    IF IS_BUTTON_PRESSED 0 DPADUP
       selec -= 1
       b_active = TRUE
    ENDIF
    IF IS_BUTTON_PRESSED 0 DPADDOWN
        selec += 1
        b_active = TRUE
    ENDIF
ELSE
    IF NOT IS_BUTTON_PRESSED 0 DPADUP
    AND NOT IS_BUTTON_PRESSED 0 DPADDOWN
        b_active = FALSE
    ENDIF
ENDIF
IF selec < 0
    selec = num_items
ENDIF
IF selec > num_items
    selec = 0
ENDIF
selec_mult =# selec
selec_mult *= 22.0
selec_mult += 291.2
DRAW_RECT 560.0 selec_mult SCREEN_WIDTH 22.0 theme_r theme_g theme_b 255
RETURN


set_app_title:
SET_TEXT_EDGE 0 0 0 0 0
SET_TEXT_JUSTIFY 2
SET_TEXT_COLOUR 255 255 255 255
SET_TEXT_SCALE 0.32 1.6
RETURN

set_settings_item:
SET_TEXT_EDGE 0 0 0 0 0
SET_TEXT_COLOUR 0 0 0 255
SET_TEXT_SCALE 0.28 1.4
RETURN

draw_phone_frame:
LOAD_SPRITE 1 phone
LOAD_SPRITE 2 frame
DRAW_SPRITE 1 560.0 340.0 105.0 250.0 255 255 255 255
DRAW_SPRITE 2 560.0 340.0 105.0 250.0 theme_r theme_g theme_b 255
RETURN

rainbow_theme:
IF dummy = 1
    theme_r--
    IF theme_r < 120
        theme_r = 120
        dummy = 2
    ENDIF
ENDIF
IF dummy = 2
    theme_g++
    IF theme_g > 255
        theme_g = 255
        dummy = 3
    ENDIF
ENDIF
IF dummy = 3
    theme_g--
    IF theme_g < 70
        theme_g = 70
        dummy = 4
    ENDIF
ENDIF
IF dummy = 4
    theme_b++
    IF theme_b > 255
        theme_b = 255
        dummy = 5
    ENDIF
ENDIF
IF dummy = 5
    theme_r++
    IF theme_r > 255
        theme_r = 255
        dummy = 6
    ENDIF
ENDIF
IF dummy = 6
    theme_b--
    IF theme_b < 180
        theme_b = 180
        dummy = 7
    ENDIF
ENDIF
IF dummy = 7
    theme_r--
    IF theme_r < 40
        theme_r = 40
        dummy = 8
    ENDIF
ENDIF
IF dummy = 8
    theme_g++
    IF theme_g > 255
        theme_g = 255
        dummy = 9
    ENDIF
ENDIF
IF dummy = 9
    theme_b--
    IF theme_b < 40
        theme_b = 40
        dummy = 10
    ENDIF
ENDIF
IF dummy = 10
    theme_r++
    IF theme_r > 255
        theme_r = 255
        dummy = 11
    ENDIF
ENDIF
IF dummy = 11
    theme_g--
    IF theme_g < 30
        theme_g = 30
        dummy = 1
    ENDIF
ENDIF
RETURN

}
SCRIPT_END