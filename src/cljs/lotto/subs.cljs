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
  
