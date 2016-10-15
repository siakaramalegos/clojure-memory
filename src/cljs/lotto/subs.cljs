(ns lotto.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
 :app-state
 (fn [db]
   db))
  
(re-frame/reg-sub
  :current-player
   (fn [db]
     (get db :current-player)))
  
(re-frame/reg-sub
  :card-at
   (fn [db, [_ x y]]
     ;;(get (get (get db :cards) y) x)))
     (get-in db [:cards y x])))

(re-frame/reg-sub
  :grid-size
   (fn [db]
     {:width (count (first (get db :cards)))
      :height (count (get db :cards))}))

