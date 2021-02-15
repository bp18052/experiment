;;サイズ
(define size_x 400)
(define size_y 500)
;;Color
(define red(255 0 0))
(define blue (0 0 255))
(define green (0 255 0))
(define black (255 255 255))
(define white (0 0 0))
;; 円周率
(define PI 3.1415926)
;;turtle
(define (turtle)
	
)
;; ペンの状態

(define (pen x)
  (if (eq? x 'down) (set! pendown #t) (set! pendown #f)))

(define (forward distance)
  (let ((nx (+ tx (* distance (cos (/ (* PI direction) 180)))))
	(ny (+ ty (* distance (sin (/ (* PI direction) 180))))))
    (if pendown (draw-line tx ty nx ny))
    (set! tx nx)
    (set! ty ny)))
 
;;turn 角度
(define (turn angle)
  (set! direction (+ direction angle)))
 
;;koch-curve-draw(コッホ描画)
(define (koch-curve-draw length level)
  (if (= level 0) 
      (forward length)
      (let ((next-length (/ length 3)))
	(koch-curve-draw next-length (- level 1))

	(turn 60)
	(koch-curve-draw next-length (- level 1))

	(turn -120)
	(koch-curve-draw next-length (- level 1))

	(turn 60)
	(koch-curve-draw next-length (- level 1)))))


(define (koch-curve length level)
  (pen 'down)
  (koch-curve-draw length level))
  

;;koch-snow-draw
(define(koch-snow-draw num)
	(if(= num 0)
		#f(begin
			(koch-curve-draw 100 5)
			(koch-snow-draw (- num 1)))))

(koch-snow-draw 3)