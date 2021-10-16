SCRIPT_START
{
SCRIPT_NAME VMENU
NOP
LVAR_INT selected b_active vehicle scplayer max_item veh_model r g b dummy
LVAR_FLOAT items_size items_pos sc_float sc_pos item_qt px py pz pa

CONST_INT MAX_ITEMS 2
CONST_FLOAT ITEM_QUANTITY 3.0

CONST_INT MAX_STYLE 2
CONST_FLOAT STYLE_QUANTITY 3.0

CONST_FLOAT RECT_X 112.0
CONST_FLOAT RECT_W 170.0


GET_PLAYER_CHAR 0 scplayer

selected = 0
dummy = 1
r = 120
g = 60
b = 220

init:
WAIT 0
IF IS_BUTTON_PRESSED 0 RIGHTSHOCK
AND IS_BUTTON_PRESSED 0 LEFTSHOCK
    SET_PLAYER_CONTROL 0 0
    GOTO main_menu_loop
ENDIF
GOTO init

main_menu_loop:
WHILE IS_BUTTON_PRESSED 0 CROSS
OR IS_BUTTON_PRESSED 0 TRIANGLE
    GOSUB draw_main_menu
ENDWHILE
GOSUB draw_main_menu
IF IS_BUTTON_PRESSED 0 CROSS
    SWITCH selected
        CASE 0
            GOTO style_loop
            BREAK
        CASE 1
            selected = 0
            GOTO cars_loop
            BREAK
        CASE 2
            BREAK
    ENDSWITCH
ENDIF
IF IS_BUTTON_PRESSED 0 TRIANGLE
AND b_active = 0
    b_active = 1
    SET_PLAYER_CONTROL 0 1
    GOTO init
ENDIF
GOTO main_menu_loop

draw_main_menu:
WAIT 0
USE_TEXT_COMMANDS 1
GOSUB draw_title
SET_TEXT_FONT 2
SET_TEXT_SCALE 0.35 1.68
SET_TEXT_COLOUR 140 120 235 255
SET_TEXT_EDGE 0 0 0 0 0
DISPLAY_TEXT 30.0 82.0 RMOPTS
DRAW_RECT RECT_X 90.0 RECT_W 20.0 0 0 0 255
item_qt = ITEM_QUANTITY
max_item = MAX_ITEMS
GOSUB create_selection
GOSUB set_textitem_params
IF selected = 0
    SET_TEXT_COLOUR 0 0 0 255
ENDIF
DISPLAY_TEXT 31.0 100.0 RMITEM1
GOSUB set_textitem_params
IF selected = 1
    SET_TEXT_COLOUR 0 0 0 255
ENDIF
DISPLAY_TEXT 31.0 120.0 RMITEM2
GOSUB set_textitem_params
IF selected = 2
    SET_TEXT_COLOUR 0 0 0 255
ENDIF
DISPLAY_TEXT 31.0 140.0 RMITEM3
USE_TEXT_COMMANDS 0
RETURN


style_loop:
WHILE IS_BUTTON_PRESSED 0 CROSS
OR IS_BUTTON_PRESSED 0 TRIANGLE
    GOSUB draw_style_items
ENDWHILE
GOSUB draw_style_items
IF IS_BUTTON_PRESSED 0 CROSS
    SWITCH selected
        CASE 0
            BREAK
        CASE 1
            BREAK
        CASE 2
            BREAK
    ENDSWITCH
ENDIF
IF IS_BUTTON_PRESSED 0 TRIANGLE
    GOTO main_menu_loop
ENDIF
GOTO style_loop

draw_style_items:
WAIT 0
USE_TEXT_COMMANDS 1
GOSUB draw_vehicle_panel
item_qt = STYLE_QUANTITY
max_item = MAX_STYLE
GOSUB create_selection
GOSUB set_textitem_params
IF selected = 0
    SET_TEXT_COLOUR 0 0 0 255
ENDIF
DISPLAY_TEXT 31.0 100.0 STSKIN
GOSUB set_textitem_params
IF selected = 1
    SET_TEXT_COLOUR 0 0 0 255
ENDIF
DISPLAY_TEXT 31.0 120.0 STCLOTH
GOSUB set_textitem_params
IF selected = 2
    SET_TEXT_COLOUR 0 0 0 255
ENDIF
DISPLAY_TEXT 31.0 140.0 STRESET
USE_TEXT_COMMANDS 0
RETURN

cars_loop:
WHILE IS_BUTTON_PRESSED 0 CROSS
OR IS_BUTTON_PRESSED 0 TRIANGLE
OR IS_BUTTON_PRESSED 0 DPADDOWN
OR IS_BUTTON_PRESSED 0 DPADUP
    GOSUB draw_cars_items
ENDWHILE
GOSUB draw_cars_items

GET_CHAR_COORDINATES scplayer px py pz
GET_CHAR_HEADING scplayer pa
pa += 90.0
GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 0.0 5.0 0.0 px py pz

IF IS_BUTTON_PRESSED 0 DPADUP
OR IS_BUTTON_PRESSED 0 DPADDOWN
    SWITCH selected
        CASE 0
            veh_model = MANANA
            gosub spawn_vehicle
            BREAK
        CASE 1
            veh_model = WASHING
            gosub spawn_vehicle
            BREAK
        CASE 2
            veh_model = BANSHEE
            gosub spawn_vehicle
            BREAK
        CASE 3
            veh_model = BUFFALO
            gosub spawn_vehicle
            BREAK
        CASE 4
            veh_model = LANDSTAL
            gosub spawn_vehicle
            BREAK
        CASE 5
            veh_model = FELTZER
            gosub spawn_vehicle
            BREAK
        CASE 6
            veh_model = TOPFUN
            gosub spawn_vehicle
            BREAK
        CASE 7
            veh_model = BANDITO
            gosub spawn_vehicle
            BREAK
        CASE 8
            veh_model = RANCHER
            gosub spawn_vehicle
            BREAK
        CASE 9
            veh_model = RNCHLURE
            gosub spawn_vehicle
            BREAK
    ENDSWITCH
ENDIF
IF IS_BUTTON_PRESSED 0 SQUARE
    WARP_CHAR_INTO_CAR scplayer vehicle
    SET_PLAYER_CONTROL 0 1
    GOTO init
ENDIF
IF IS_BUTTON_PRESSED 0 TRIANGLE
    selected = 0
    GOTO main_menu_loop
ENDIF
IF IS_BUTTON_PRESSED 0 DPADDOWN
AND selected = 9
    selected = 0
    GOTO cars2_loop
ENDIF
GOTO cars_loop

draw_cars_items:
WAIT 0
USE_TEXT_COMMANDS 1
GOSUB draw_vehicle_panel
item_qt = 10.0
max_item = 9
GOSUB create_selection

GOSUB set_textitem_params
IF selected = 0
    SET_TEXT_COLOUR 0 0 0 255
ENDIF
DISPLAY_TEXT 31.0 100.0 MANANA

GOSUB set_textitem_params
IF selected = 1
    SET_TEXT_COLOUR 0 0 0 255
ENDIF
DISPLAY_TEXT 31.0 120.0 WASHING

GOSUB set_textitem_params
IF selected = 2
    SET_TEXT_COLOUR 0 0 0 255
ENDIF
DISPLAY_TEXT 31.0 140.0 BANSHEE

GOSUB set_textitem_params
IF selected = 3
    SET_TEXT_COLOUR 0 0 0 255
ENDIF
DISPLAY_TEXT 31.0 160.0 BUFFALO

GOSUB set_textitem_params
IF selected = 4
    SET_TEXT_COLOUR 0 0 0 255
ENDIF
DISPLAY_TEXT 31.0 180.0 LANDSTK
GOSUB set_textitem_params
IF selected = 5
    SET_TEXT_COLOUR 0 0 0 255
ENDIF
DISPLAY_TEXT 31.0 200.0 FELTZER

GOSUB set_textitem_params
IF selected = 6
    SET_TEXT_COLOUR 0 0 0 255
ENDIF
DISPLAY_TEXT 31.0 220.0 TOPFUN

GOSUB set_textitem_params
IF selected = 7
    SET_TEXT_COLOUR 0 0 0 255
ENDIF
DISPLAY_TEXT 31.0 240.0 BANDITO

GOSUB set_textitem_params
IF selected = 8
    SET_TEXT_COLOUR 0 0 0 255
ENDIF
DISPLAY_TEXT 31.0 260.0 RANCHER

GOSUB set_textitem_params
IF selected = 9
    SET_TEXT_COLOUR 0 0 0 255
ENDIF
DISPLAY_TEXT 31.0 280.0 RANCHER

USE_TEXT_COMMANDS 0
RETURN

cars2_loop:
WHILE IS_BUTTON_PRESSED 0 CROSS
OR IS_BUTTON_PRESSED 0 TRIANGLE
OR IS_BUTTON_PRESSED 0 DPADDOWN
OR IS_BUTTON_PRESSED 0 DPADUP
    GOSUB draw_cars2_items
ENDWHILE
GOSUB draw_cars2_items
IF IS_BUTTON_PRESSED 0 DPADUP
OR IS_BUTTON_PRESSED 0 DPADDOWN
    SWITCH selected
        CASE 0
            veh_model = SABRE
            gosub spawn_vehicle2
            BREAK
        CASE 1
            veh_model = BLISTAC
            gosub spawn_vehicle2
            BREAK
        CASE 2
            veh_model = VIRGO
            gosub spawn_vehicle2
            BREAK
        CASE 3
            veh_model = STALLION
            gosub spawn_vehicle2
            BREAK
        CASE 4
            veh_model = GLENSHIT
            gosub spawn_vehicle2
            BREAK
        CASE 5
            veh_model = INFERNUS
            gosub spawn_vehicle2
            BREAK
        CASE 6
            veh_model = HOTRING
            gosub spawn_vehicle2
            BREAK
        CASE 7
            veh_model = COMET
            gosub spawn_vehicle2
            BREAK
        CASE 8
            veh_model = SUPERGT
            gosub spawn_vehicle2
            BREAK
        CASE 9
            veh_model = SADLER
            gosub spawn_vehicle2
            BREAK
    ENDSWITCH
ENDIF
IF IS_BUTTON_PRESSED 0 SQUARE
    WARP_CHAR_INTO_CAR scplayer vehicle
    SET_PLAYER_CONTROL 0 1
    GOTO init
ENDIF
IF IS_BUTTON_PRESSED 0 TRIANGLE
    selected = 0
    GOTO main_menu_loop
ENDIF
IF IS_BUTTON_PRESSED 0 DPADUP
AND selected = 0
    selected = 9
    GOTO cars_loop
ENDIF
GOTO cars2_loop

draw_cars2_items:
WAIT 0
USE_TEXT_COMMANDS 1
GOSUB draw_vehicle_panel
item_qt = 10.0
max_item = 9
GOSUB create_selection

GOSUB set_textitem_params
IF selected = 0
    SET_TEXT_COLOUR 0 0 0 255
ENDIF
DISPLAY_TEXT 31.0 100.0 SABRE

GOSUB set_textitem_params
IF selected = 1
    SET_TEXT_COLOUR 0 0 0 255
ENDIF
DISPLAY_TEXT 31.0 120.0 BLISTAC

GOSUB set_textitem_params
IF selected = 2
    SET_TEXT_COLOUR 0 0 0 255
ENDIF
DISPLAY_TEXT 31.0 140.0 VIRGO

GOSUB set_textitem_params
IF selected = 3
    SET_TEXT_COLOUR 0 0 0 255
ENDIF
DISPLAY_TEXT 31.0 160.0 STALION

GOSUB set_textitem_params
IF selected = 4
    SET_TEXT_COLOUR 0 0 0 255
ENDIF
DISPLAY_TEXT 31.0 180.0 GLENSHI
GOSUB set_textitem_params
IF selected = 5
    SET_TEXT_COLOUR 0 0 0 255
ENDIF
DISPLAY_TEXT 31.0 200.0 INFERNU

GOSUB set_textitem_params
IF selected = 6
    SET_TEXT_COLOUR 0 0 0 255
ENDIF
DISPLAY_TEXT 31.0 220.0 HOTRING

GOSUB set_textitem_params
IF selected = 7
    SET_TEXT_COLOUR 0 0 0 255
ENDIF
DISPLAY_TEXT 31.0 240.0 COMET

GOSUB set_textitem_params
IF selected = 8
    SET_TEXT_COLOUR 0 0 0 255
ENDIF
DISPLAY_TEXT 31.0 260.0 SUPERGT

GOSUB set_textitem_params
IF selected = 9
    SET_TEXT_COLOUR 0 0 0 255
ENDIF
DISPLAY_TEXT 31.0 280.0 SADLER

USE_TEXT_COMMANDS 0
RETURN


draw_vehicle_panel:
GOSUB draw_title
SET_TEXT_FONT 2
SET_TEXT_SCALE 0.35 1.68
SET_TEXT_COLOUR 140 120 235 255
SET_TEXT_EDGE 0 0 0 0 0
DISPLAY_TEXT 38.0 82.0 RMITEM2
DRAW_RECT RECT_X 90.0 RECT_W 20.0 0 0 0 255
RETURN

draw_title:
SET_TEXT_FONT 0
SET_TEXT_EDGE 1 0 0 0 255
SET_TEXT_COLOUR 255 255 255 255
SET_TEXT_SCALE 1.0 4.0
DISPLAY_TEXT 72.0 32.0 RMMENU
GOSUB pulse_color
DRAW_RECT RECT_X 45.0 RECT_W 70.0 r g b 235
DRAW_RECT 558.0 414.0 100.0 23.0 0 0 0 235
GOSUB set_textitem_params
DISPLAY_TEXT 558.0 405.0 RMSELEC
GOSUB set_textitem_params
DISPLAY_TEXT 515.0 405.0 RMBACK
RETURN

set_textitem_params:
SET_TEXT_FONT 1
SET_TEXT_SCALE 0.35 1.68
SET_TEXT_WRAPX 640.0
SET_TEXT_COLOUR 255 255 255 255
SET_TEXT_EDGE 0 0 0 0 0
RETURN

create_selection:
items_pos = item_qt * 20.0
items_pos /= 2.0 
items_pos += 100.0
items_size = item_qt * 20.0 
DRAW_RECT RECT_X items_pos RECT_W items_size 0 0 0 235
IF b_active = FALSE
    IF IS_BUTTON_PRESSED 0 DPADUP
       selected -= 1
       b_active = TRUE
    ENDIF
    IF IS_BUTTON_PRESSED 0 DPADDOWN
        selected += 1
        b_active = TRUE
    ENDIF
ELSE
    IF NOT IS_BUTTON_PRESSED 0 DPADUP
    AND NOT IS_BUTTON_PRESSED 0 DPADDOWN
        b_active = FALSE
    ENDIF
ENDIF
IF selected < 0
    selected = max_item
ENDIF
IF selected > max_item
    selected = 0
ENDIF
sc_float =# selected
sc_float *= 20.0
sc_pos = sc_float + 110.0
DRAW_RECT RECT_X sc_pos RECT_W 20.0 255 255 255 220
RETURN

spawn_vehicle:
REQUEST_MODEL veh_model
WHILE NOT HAS_MODEL_LOADED veh_model
    GOSUB draw_cars_items
ENDWHILE
CREATE_CAR veh_model px py pz vehicle
SET_CAR_HEADING vehicle pa
MARK_CAR_AS_NO_LONGER_NEEDED vehicle
RETURN

spawn_vehicle2:
REQUEST_MODEL veh_model
WHILE NOT HAS_MODEL_LOADED veh_model
    GOSUB draw_cars2_items
ENDWHILE
CREATE_CAR veh_model px py pz vehicle
SET_CAR_HEADING vehicle pa
MARK_CAR_AS_NO_LONGER_NEEDED vehicle
RETURN

pulse_color:
IF dummy = 1
    r--
    g--
    b--
    IF g < 60
        IF r < 120
            r = 120
        ENDIF
        IF b < 220
            b = 220
        ENDIF
        g = 60
        dummy = 2
    ENDIF
ELSE
    r++
    g++
    b++
    IF r > 255
        r = 255
        dummy = 1
    ENDIF
    IF g > 255
        g = 255
        dummy = 1
    ENDIF
    IF b > 255
        b = 255
        dummy = 1
    ENDIF
ENDIF

RETURN
}
SCRIPT_END